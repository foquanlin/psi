<template>
  <el-drawer title="商品详情" :close-on-click-modal="false" size="90%" :visible.sync="visible">
    <el-card shadow="never" style="margin-right: 10px;margin-left: 10px">
      <el-row>
        <el-col :span="6">
          <el-image fit="contain" style="width:200px" :src="goods.picUrls"></el-image>
        </el-col>
        <el-col :span="18">
        <el-descriptions :column="2" style="margin-left: 10px">
          <el-descriptions-item label="商品名称">{{goods.name}}</el-descriptions-item>
          <el-descriptions-item label="商品分类">{{goods.catalog?goods.catalog.name:''}}</el-descriptions-item>
          <el-descriptions-item label="商品编码">{{goods.no}}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{goods.createDate}}</el-descriptions-item>
          <el-descriptions-item label="单位">{{goods.unit?goods.unit.name:''}}</el-descriptions-item>
          <el-descriptions-item label="库存">{{goods.warehouseNum}}</el-descriptions-item>
          <el-descriptions-item label="备注" :span="2">{{goods.memo}}</el-descriptions-item>
        </el-descriptions>
        </el-col>
      </el-row>
      <el-table border :data="dataList" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
        <el-table-column prop="specName" header-align="center" align="left" label="规格" width="280"/>
        <el-table-column prop="status" header-align="center" align="center" label="状态" width="80">
          <template v-slot="scope">
            <span v-if="isAuth('psi:goods:updown')">
              <el-popover v-if="scope.row.status == 'UP'" placement="top-start" title="提示" width="200" trigger="hover" content="点击,下架商品。">
                <el-tag slot="reference" type="success" @click="updownHandle(scope.row)">上架</el-tag>
              </el-popover>
              <el-popover v-else placement="top-start" title="提示" width="200" trigger="hover" content="点击,上架商品。">
                <el-tag slot="reference"  @click="updownHandle(scope.row)">下架</el-tag>
              </el-popover>
            </span>
            <span v-else>
              <el-tag v-if="scope.row.status === 'UP'" size="small" >上架</el-tag>
              <el-tag v-if="scope.row.status === 'DOWN'" size="small" type="danger">下架</el-tag>
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="barcode" header-align="center" align="center" label="条形码">
          <template v-slot="scope">
            <el-input v-if="scope.row.edited" v-model="scope.row.barcode" placeholder="条形码" size="mini"/>
            <span v-else>{{scope.row.barcode}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="costPrice" header-align="center" align="center" label="参考进价">
          <template v-slot="scope">
            <el-input-number v-if="scope.row.edited" v-model="scope.row.costPrice" placeholder="参考进价" size="mini"/>
            <span v-else>{{scope.row.costPrice}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="salePrice" header-align="center" align="center" label="参考售价">
          <template v-slot="scope">
            <el-input-number v-if="scope.row.edited" v-model="scope.row.salePrice" placeholder="参考售价" size="mini"/>
            <span v-else>{{scope.row.salePrice}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="warehouseNum" header-align="center" align="center" label="仓库库存"/>
        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
          <template v-slot="scope">
            <el-button v-if="isAuth('psi:goods:update') && scope.row.edited === true" type="text" size="small" @click="updateHandle(scope.row)">保存</el-button>
            <el-button v-if="isAuth('psi:goods:update') && !scope.row.edited" type="text" size="small" @click="modifyHandle(scope.row, scope.$index)">修改</el-button>
            <el-button v-if="isAuth('psi:goods:delete')" type="text" size="small" @click="deleteHandle(scope.row, scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button style="margin-top: 10px;" size="mini" icon="el-icon-plus" @click="addSkuGoods" >添加明细</el-button>
<!--      <span class="dialog-footer">-->
<!--      <el-button @click="visible = false">关闭</el-button>-->
<!--    </span>-->
    </el-card>
    <goods-sku v-if="goodsSkuVisible" ref="goodsSku"  @refreshDataList="getDataList"/>
  </el-drawer>
</template>

<script>
  import GoodsSku from './goods-sku'
  export default {
    data () {
      return {
        id: '',
        visible: false,
        goods: {},
        dataList: [],
        goodsSkuVisible: false
      }
    },
    components: {
      GoodsSku
    },
    methods: {
      init (id) {
        this.id = id || ''
        this.goods = {}
        this.dataList = []
        this.visible = true
        this.getDataList()
      },
      getDataList () {
        this.$nextTick(() => {
          if (this.id) {
            this.$http({
              url: `/psi/goods/info/${this.id}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                let skuList = data.info.skuList
                data.info.skuList = null
                this.goods = data.info
                skuList.forEach(item => {
                  item.edited = false
                })
                this.dataList = skuList
              }
            })
          }
        })
      },
      updateHandle (row) {
        if (row.edited) {
          this.$http({
            url: `/psi/goodssku/update`,
            method: 'post',
            data: row
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({ message: '操作成功', type: 'success', duration: 1500 })
              row.edited = false
            }
          })
        }
      },
      modifyHandle (row, index) {
        row.edited = true
      },
      deleteHandle (row, index) {
        this.$confirm(`确定进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: `/psi/goodssku/delete`,
            method: 'post',
            data: [row.id]
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({ message: '操作成功', type: 'success', duration: 1500 })
              this.dataList.splice(index, 1)
            }
          })
        })
      },
      updownHandle (row) {
        this.$http({
          url: `/psi/goodssku/updown`,
          method: 'post',
          params: {id: row.id}
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({ message: '操作成功', type: 'success', duration: 1500 })
            row.status = (row.status === 'UP') ? 'DOWN' : 'UP'
          }
        })
      },
      addSkuGoods () {
        this.goodsSkuVisible = true
        this.$nextTick(() => {
          this.$refs.goodsSku.init(this.id)
        })
      }
    }
  }
</script>
