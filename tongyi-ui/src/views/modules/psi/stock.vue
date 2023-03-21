<template>
  <div class="mod-stock">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
<!--      <el-form-item>-->
<!--        <el-date-picker v-model="searchForm.orderNo" placeholder="关联单号" clearable/>-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-date-picker v-model="searchForm.createTimeStart" placeholder="开始时间" clearable type="date" value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createTimeEnd" placeholder="结束时间" clearable type="date" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.type" placeholder="出入库" clearable>
          <el-option value="IN" label="入库"></el-option>
          <el-option value="OUT" label="出库"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.catalog" placeholder="出入类型" clearable>
          <el-option value="TIAOZHENG" label="库存调整"></el-option>
          <el-option value="DIAOBO" label="库存调拨"></el-option>
          <el-option value="PANDIAN" label="库存盘点"></el-option>
          <el-option value="CAIGOU" label="采购"></el-option>
          <el-option value="DINGDAN" label="订单"></el-option>
          <el-option value="XIAOSHOU" label="销售"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.goodsId" placeholder="商品名称" clearable filterable loading-text="加载中..." @focus="loadGoods" @change="changeGoods">
          <el-option v-for="item in goodsList" :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.skuId" :disabled="!searchForm.goodsId" placeholder="规格" clearable filterable loading-text="加载中..." @focus="loadSku">
          <el-option v-for="item in skuList" :key="item.id" :label="item.specName" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.warehouseIds" placeholder="仓库" clearable :multiple="true">
          <el-option v-for="item in warehouseList" :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.skuStatus" placeholder="上架/下架" clearable>
          <el-option value="UP" label="上架"></el-option>
          <el-option value="DOWN" label="下架"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-radio-group v-model="searchForm.supplierType" @change="changeSupplier">
          <el-radio-button label="CUSTOMER">客户</el-radio-button>
          <el-radio-button label="SUPPLIER">供应商</el-radio-button>
        </el-radio-group>
        <el-select v-model="searchForm.supplierName" placeholder="客户供应商" clearable filterable loading-text="加载中..." @focus="loadSupplier">
          <el-option v-for="item in supplierList" :key="item.id" :label="item.name" :value="item.name"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
<!--        <el-button v-if="isAuth('psi:stock:save')" type="primary" @click="editHandle()">新增</el-button>-->
<!--        <el-button v-if="isAuth('psi:stock:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>-->
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" style="width: 100%;">
      <el-table-column prop="goodsName" header-align="center" align="left" fixed="fixed" label="商品">
        <template v-slot="scope">
          <span @click="showDetails(scope.row)">{{scope.row.goodsName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="skuId" header-align="center" align="left" label="规格" width="250">
        <template v-slot="scope">
          <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="catalog" header-align="center" align="left" label="出入库分类">
        <template v-slot="scope">
          <span v-if="scope.row.catalog === 'TIAOZHENG'">库存调整</span>
          <span v-else-if="scope.row.catalog === 'PANDIAN'">库存盘点</span>
          <span v-else-if="scope.row.catalog === 'DIAOBO'">库存调拨</span>
          <span v-else-if="scope.row.catalog === 'CAIGOU'">采购</span>
          <span v-else-if="scope.row.catalog === 'XIAOSHOU'">销售</span>
          <span v-else-if="scope.row.catalog === 'DINGDAN'">订单</span>
        </template>
      </el-table-column>
      <el-table-column prop="type" header-align="center" align="left" label="出入库类型">
        <template v-slot="scope">
          <span v-if="scope.row.type === 'IN'">入库</span>
          <span v-else-if="scope.row.type === 'OUT'">出库</span>
        </template>
      </el-table-column>
      <el-table-column prop="warehouseName" header-align="center" align="left" label="仓库"/>
      <el-table-column prop="supplierName" header-align="center" align="left" label="客户供应商"/>
      <el-table-column prop="num" header-align="center" align="right" label="数量"/>
      <el-table-column prop="createTime" header-align="center" align="left" label="时间" width="100"/>
      <el-table-column prop="costPrice" header-align="center" align="right" label="平均进价"/>
      <el-table-column prop="salePrice" header-align="center" align="right" label="平均售价"/>
<!--      <el-table-column prop="orderId" header-align="center" align="left" label="关联单号"/>-->
      <el-table-column prop="createName" header-align="center" align="left" label="操作人"/>
      <el-table-column fixed="right" header-align="center" align="center" width="80" label="操作">
        <template v-slot="scope">
<!--          <el-button v-if="isAuth('psi:stock:info')" type="text" size="small" @click="showDetails(scope.row.id)">查看</el-button>-->
<!--          <el-button v-if="isAuth('psi:stock:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>-->
          <el-button v-if="isAuth('psi:stock:delete') && scope.row.catalog==='TIAOZHENG'" type="text" size="small" @click="deleteHandle(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <goods-detail v-if="detailVisible" ref="goodsDetail"/>
  </div>
</template>

<script>
  import GoodsDetail from './goods-detail'
  export default {
    data () {
      return {
        searchForm: {
          orderNo: '',
          createTimeStart: '',
          createTimeEnd: '',
          catalog: '',
          type: '',
          goodsId: '',
          skuId: '',
          warehouseIds: [],
          skuStatus: '',
          supplierType: '',
          supplierName: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        detailVisible: false,
        warehouseList: [],
        goodsList: [],
        skuList: [],
        supplierList: []
      }
    },
    components: {
      GoodsDetail
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
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/stock/list',
          method: 'post',
          loading: false,
          data: {
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
      // 查看详情
      showDetails (row) {
        this.detailVisible = true
        this.$nextTick(() => {
          this.$refs.goodsDetail.init(row.goodsId)
        })
      },
      // 删除
      deleteHandle (row) {
        this.$confirm(`确定对[${row.goodsName}>>${row.specName}]进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: '/psi/stock/delete',
            method: 'post',
            data: [ row.id ]
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({ message: '操作成功', type: 'success', duration: 1500 })
              this.getDataList()
            }
          })
        })
      },
      loadGoods () {
        if (this.goodsList.length > 0) {
          return
        }
        this.$http({
          url: '/psi/goods/listAll',
          method: 'get',
          loading: false,
          params: {}
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.goodsList = data.list
          } else {
            this.goodsList = []
          }
        })
      },
      loadSku () {
        if (this.skuList.length > 0) {
          return
        }
        this.$http({
          url: '/psi/goodssku/listAll',
          method: 'get',
          loading: false,
          params: {
            goodsId: this.searchForm.goodsId
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.skuList = data.list
          } else {
            this.skuList = []
          }
        })
      },
      changeGoods () {
        this.searchForm.skuId = undefined
        this.skuList = []
      },
      loadSupplier () {
        if (this.supplierList.length > 0) {
          return
        }
        this.$http({
          url: '/psi/supplier/listAll',
          method: 'get',
          loading: false,
          params: {
            type: this.searchForm.supplierType
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.supplierList = data.list
          } else {
            this.supplierList = []
          }
        })
      },
      changeSupplier () {
        this.supplierList = []
      }
    }
  }
</script>
<style>
.input-with-select {
  background-color: #fff;
}
</style>
