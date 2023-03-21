<template>
  <div class="mod-goods">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="searchForm.no" placeholder="编码" clearable suffix-icon="el-icon-search"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.name" placeholder="名称" clearable suffix-icon="el-icon-search"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.barcode" placeholder="条形码,支持扫码枪录入" clearable suffix-icon="el-icon-search"/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.catalogId" placeholder="分类" clearable>
          <el-option v-for="item in catalogList" :key="item.value" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.warehouseId" multiple placeholder="仓库" clearable>
          <el-option v-for="item in warehouseList" :key="item.value" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.status" placeholder="上/下架" clearable>
          <el-option value="up" label="上架"></el-option>
          <el-option value="down" label="下架"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:goods:save')" type="primary" @click="addHandle()">新增商品</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="name" header-align="center" align="center" label="名称">
        <template v-slot="scope">
          <div style="display: flex">
            <el-popover placement="right-start" trigger="hover">
              <el-image fit="contain" style="width:400px" @click="openImg(scope.row.picUrls)" :src="scope.row.picUrls"/>
              <img slot="reference" style="height: 50px;width: 50px;" :src="scope.row.picUrls"/>
            </el-popover>
            <div style="flex: 9;text-align: left;margin-left: 5px" >{{scope.row.name}}</div>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="catalogName" header-align="center" align="left" label="分类"/>
      <el-table-column prop="no" header-align="center" align="left" label="编码"/>
      <el-table-column prop="createDate" header-align="center" align="left" label="创建时间"/>
      <el-table-column prop="unitName" header-align="center" align="left" label="单位"/>
      <el-table-column prop="costPrice" header-align="center" align="right" label="参考进价"/>
      <el-table-column prop="salePrice" header-align="center" align="right" label="参考售价"/>
      <el-table-column prop="warehouseNum" header-align="center" align="right" label="库存"/>
      <el-table-column prop="memo" header-align="center" align="left" label="备注" show-overflow-tooltip/>
      <el-table-column fixed="right" header-align="center" align="center" width="200" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:goods:outstock')" type="text" size="small" @click="addSkuGoods(scope.row)">商品明细</el-button>
          <el-button v-if="isAuth('psi:goods:update')" type="text" size="small" @click="skuHandle(scope.row)">规格管理</el-button>
          <el-button v-if="isAuth('psi:goods:info')" type="text" size="small" @click="showDetails(scope.row)">详情</el-button>
          <el-button v-if="isAuth('psi:goods:update')" type="text" size="small" @click="editHandle(scope.row)">修改</el-button>
          <el-button v-if="isAuth('psi:goods:instock')" type="text" size="small" @click="inStockHandle(scope.row)">入库</el-button>
          <el-button v-if="isAuth('psi:goods:outstock')" type="text" size="small" @click="outStockHandle(scope.row)">出库</el-button>
          <el-button v-if="isAuth('psi:goods:delete')" type="text" size="small" @click="deleteHandle(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <goods-add v-if="addVisible" ref="goodsAdd" @refreshDataList="getDataList"/>
    <goods-edit v-if="editVisible" ref="goodsEdit" @refreshDataList="getDataList"/>
    <goods-detail v-if="detailVisible" ref="goodsDetail"/>
    <goods-sku v-if="goodsSkuVisible" ref="goodsSku"/>
    <in-stock v-if="inStockVisible" ref="inStock" @refreshDataList="getDataList"/>
    <out-stock v-if="outStockVisible" ref="outStock" @refreshDataList="getDataList"/>
    <goods-spec v-if="specVisible" ref="goodsSpec"></goods-spec>
  </div>
</template>

<script>
  import GoodsAdd from './goods-add'
  import GoodsEdit from './goods-edit'
  import Options from '../sys/options'
  import InStock from './goods-instock'
  import OutStock from './goods-outstock'
  import GoodsDetail from './goods-detail'
  import GoodsSku from './goods-sku'
  import GoodsSpec from './goodsspec'
  export default {
    data () {
      return {
        searchForm: {
          name: '',
          warehouseId: '',
          catalogId: '',
          no: '',
          barcode: '',
          status: ''
        },
        dataList: [],
        warehouseList: [],
        catalogList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListSelections: [],
        addVisible: false,
        inStockVisible: false,
        outStockVisible: false,
        detailVisible: false,
        skuVisible: false,
        goodsSkuVisible: false,
        specVisible: false,
        editVisible: false
      }
    },
    components: {
      GoodsAdd,
      GoodsEdit,
      Options,
      InStock,
      OutStock,
      GoodsDetail,
      GoodsSku,
      GoodsSpec
    },
    activated () {
      this.$http({
        url: '/psi/warehouse/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.warehouseList = data.list
        } else {
          this.warehouseList = []
        }
      })
      this.$http({
        url: '/psi/catalog/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.catalogList = data.list
        } else {
          this.catalogList = []
        }
      })
      this.getDataList()
    },
    methods: {
      formatBool: (row, column, cellValue, index) => Options.formatArray(Options.yesno, cellValue),
      formatSex: (row, column, cellValue, index) => Options.formatArray(Options.genders, cellValue),
      formatDate: (row, column, cellValue, index) => Options.formatDate(row, column, cellValue, index),
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/goods/list',
          method: 'get',
          params: {
            page: this.pageIndex,
            limit: this.pageSize,
            ...this.searchForm
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.total
          } else {
            this.dataList = []
            this.totalPage = 0
          }
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 查看详情
      showDetails (row) {
        this.detailVisible = true
        this.$nextTick(() => {
          this.$refs.goodsDetail.init(row.id)
        })
      },
      // 新增 / 修改
      addHandle () {
        this.addVisible = true
        this.$nextTick(() => {
          this.$refs.goodsAdd.init(undefined)
        })
      },
      editHandle (row) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.goodsEdit.init(row.id)
        })
      },
      // 删除
      deleteHandle (row) {
        this.$confirm(`确定对[${row.name}]进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: '/psi/goods/delete',
            method: 'post',
            data: [row.id]
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({ message: '操作成功', type: 'success', duration: 1500 })
              this.getDataList()
            }
          })
        })
      },
      inStockHandle (row) {
        this.inStockVisible = true
        this.$nextTick(() => {
          this.$refs.inStock.init(row.id)
        })
      },
      outStockHandle (row) {
        this.outStockVisible = true
        this.$nextTick(() => {
          this.$refs.outStock.init(row.id)
        })
      },
      skuHandle (row) {
        this.specVisible = true
        this.$nextTick(() => {
          this.$refs.goodsSpec.init(row.id)
        })
      },
      addSkuGoods (row) {
        this.goodsSkuVisible = true
        this.$nextTick(() => {
          this.$refs.goodsSku.init(row.id)
        })
      }
    }
  }
</script>
