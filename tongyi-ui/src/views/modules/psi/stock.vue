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
        <select-stock-type v-model="searchForm" field="type"/>
      </el-form-item>
      <el-form-item>
        <select-stock-catalog v-model="searchForm" field="catalog"/>
      </el-form-item>
      <el-form-item>
        <select-goods v-model="searchForm" field="goodsId" @change="changeGoods"/>
      </el-form-item>
      <el-form-item>
        <select-sku v-model="searchForm" :goods-id="searchForm.goodsId" field="skuId"></select-sku>
      </el-form-item>
      <el-form-item>
        <select-warehouse v-model="searchForm" :multiple="true" field="warehouseIds"/>
      </el-form-item>
      <el-form-item>
        <select-sku-status v-model="searchForm" field="skuStatus"/>
      </el-form-item>
      <el-form-item>
        <select-supplier v-model="searchForm"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
<!--        <el-button v-if="isAuth('psi:stock:save')" type="primary" @click="editHandle()">新增</el-button>-->
<!--        <el-button v-if="isAuth('psi:stock:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>-->
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" style="width: 100%;">
      <el-table-column prop="goodsName" header-align="center" align="left" fixed="left" label="商品" width="250">
        <template v-slot="scope">
          <img-popover :name="scope.row.goodsName" :urls="scope.row.goodsPicUrls"/>
        </template>
<!--        <template v-slot="scope">-->
<!--          <span @click="showDetails(scope.row)">{{scope.row.goodsName}}</span>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column prop="skuId" header-align="center" align="left" label="规格" width="250">
        <template v-slot="scope">
          <span v-if="scope.row.specName">
            <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="catalog" header-align="center" align="left" label="订单分类">
        <template v-slot="scope">
          <span v-if="scope.row.catalog === 'TIAOZHENG'">库存调整</span>
          <span v-else-if="scope.row.catalog === 'PANDIAN'">库存盘点</span>
          <span v-else-if="scope.row.catalog === 'DIAOBO'">库存调拨</span>
          <span v-else-if="scope.row.catalog === 'CAIGOU'">采购</span>
          <span v-else-if="scope.row.catalog === 'XIAOSHOU'">销售</span>
          <span v-else-if="scope.row.catalog === 'DINGDAN'">订单</span>
        </template>
      </el-table-column>
      <el-table-column prop="type" header-align="center" align="left" label="出入库">
        <template v-slot="scope">
          <span v-if="scope.row.type === 'IN'">入库</span>
          <span v-else-if="scope.row.type === 'OUT'">出库</span>
        </template>
      </el-table-column>
      <el-table-column prop="warehouseName" header-align="center" align="left" label="仓库"/>
      <el-table-column prop="supplierName" header-align="center" align="left" label="客户/供应商"/>
      <el-table-column prop="orderNo" header-align="center" align="left" label="关联单号" width="140">
        <template v-slot="scope">
          <el-button v-if="scope.row.catalog ==='CAIGOU' || scope.row.catalog ==='XIAOSHOU'" type="text" size="small" @click="viewHandle(scope.row)">{{ scope.row.orderNo }}</el-button>
          <el-button v-else-if="scope.row.catalog ==='DIAOBO'" type="text" size="small" @click="allocationViewHandle(scope.row)">{{ scope.row.orderNo }}</el-button>
          <el-button v-else-if="scope.row.catalog ==='PANDIAN'" type="text" size="small" @click="checkDetailHandle(scope.row)">{{ scope.row.orderNo }}</el-button>
        </template>
      </el-table-column>
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
    <order-view v-if="orderViewVisible" ref="orderView" :descriptions="{}" />
    <allocation-view v-if="allocationViewVisible" ref="allocationView"/>
    <check-detail v-if="checkDetailVisible" ref="checkDetail"/>
  </div>
</template>

<script>
  import GoodsDetail from './goods-detail'
  import SelectSupplier from './component/select-supplier'
  import SelectWarehouse from './component/select-warehouse'
  import SelectGoods from './component/select-goods'
  import SelectSku from './component/select-sku'
  import SelectStockType from './component/select-stock-type'
  import SelectSkuStatus from './component/select-sku-status'
  import SelectStockCatalog from './component/select-stock-catalog'
  import OrderView from './order-view'
  import AllocationView from './allocation-view'
  import CheckDetail from './check-detail'
  import Options from './options'
  import ImgPopover from './component/img-popover'
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
          supplierId: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        detailVisible: false,
        orderViewVisible: false,
        allocationViewVisible: false,
        checkDetailVisible: false
      }
    },
    components: {
      ImgPopover,
      GoodsDetail,
      SelectSupplier,
      SelectWarehouse,
      SelectGoods,
      SelectSku,
      SelectStockType,
      SelectSkuStatus,
      SelectStockCatalog,
      OrderView,
      AllocationView,
      CheckDetail
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/stock/list',
          method: 'post',
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
      changeGoods (id, goods) {
        this.searchForm.goodsId = id
        this.searchForm.skuId = undefined
      },
      viewHandle (row) {
        this.orderViewVisible = true
        this.$nextTick(() => {
          let catalog = 'BUY'
          let type = 'ORDER'
          if (row.catalog === 'CAIGOU' && row.type === 'IN') {
            catalog = 'BUY'
            type = 'ORDER'
          } else if (row.catalog === 'CAIGOU' && row.type === 'OUT') {
            catalog = 'BUY'
            type = 'REFUND'
          } else if (row.catalog === 'XIAOSHOU' && row.type === 'IN') {
            catalog = 'SALE'
            type = 'REFUND'
          } else if (row.catalog === 'XIAOSHOU' && row.type === 'OUT') {
            catalog = 'SALE'
            type = 'ORDER'
          }
          this.$refs.orderView.descriptions = Options.descriptions(catalog, type)
          this.$refs.orderView.init(row.orderId)
        })
      },
      allocationViewHandle (row) {
        this.allocationViewVisible = true
        this.$nextTick(() => {
          this.$refs.allocationView.init(row.orderId)
        })
      },
      checkDetailHandle (row) {
        this.checkDetailVisible = true
        this.$nextTick(() => {
          this.$refs.checkDetail.init(row.orderId)
        })
      }
    }
  }
</script>
<style>
.input-with-select {
  background-color: #fff;
}
</style>
