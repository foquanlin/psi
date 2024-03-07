<template>
  <div class="mod-bankbill">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <select-bank v-model="searchForm" field="bankId"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createDate" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <select-supplier v-model="searchForm" field="supplierId"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1;getDataList()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" style="width: 100%;">
      <el-table-column prop="orderNo" header-align="center" align="center" label="订单编号" width="200">
        <template v-slot="scope">
          <el-button type="text" size="mini" @click="orderViewHandle(scope.row)">{{scope.row.orderNo}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="createDate" header-align="center" align="center" label="收付款日期"/>
      <el-table-column prop="bankName" header-align="center" align="center" label="银行账户"/>
      <el-table-column prop="supplierName" header-align="center" align="center" label="客户/供应商"/>
      <el-table-column prop="ownerUid" header-align="center" align="center" label="负责人"/>
      <el-table-column prop="type" header-align="center" align="center" label="类型">
        <template v-slot="scope">
          <span v-if="scope.row.orderCatalog ==='BUY' && scope.row.orderType==='ORDER'">采购付款</span>
          <span v-if="scope.row.orderCatalog ==='BUY' && scope.row.orderType==='REFUND'">采购退款</span>
          <span v-if="scope.row.orderCatalog ==='SALE' && scope.row.orderType==='ORDER'">销售收款</span>
          <span v-if="scope.row.orderCatalog ==='SALE' && scope.row.orderType==='REFUND'">销售退款</span>
        </template>
      </el-table-column>
      <el-table-column prop="inAmount" header-align="center" align="center" label="收入"/>
      <el-table-column prop="outAmount" header-align="center" align="center" label="支出"/>
      <el-table-column prop="outAmount" header-align="center" align="center" label="余额">
        <template v-slot="scope">
          {{scope.row.inAmount - scope.row.outAmount}}
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <order-view v-if="orderViewVisible" ref="orderView" :descriptions="{}" ></order-view>
  </div>
</template>

<script>
  import SelectSupplier from './component/select-supplier'
  import SelectBank from './component/select-bank'
  import OrderView from './order-view'
  import Options from './options.vue'
  export default {
    computed: {
      Options() {
        return Options
      }
    },
    data () {
      return {
        searchForm: {
          bankId: '',
          supplierId: '',
          name: '',
          createDate: []
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListSelections: [],
        editVisible: false,
        orderViewVisible: false
      }
    },
    components: {
      SelectSupplier,
      SelectBank,
      OrderView
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/bankbill/list',
          method: 'get',
          params: {
            page: this.pageIndex,
            limit: this.pageSize,
            createDateStart: this.searchForm.createDate ? this.searchForm.createDate[0] : null,
            createDateEnd: this.searchForm.createDate ? this.searchForm.createDate[1] : null,
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
      orderViewHandle (row) {
        this.orderViewVisible = true
        this.$nextTick(() => {
          this.$refs.orderView.descriptions = Options.descriptions(row.orderCatalog, row.orderType)
          this.$refs.orderView.init(row.orderId)
        })
      }
    }
  }
</script>
