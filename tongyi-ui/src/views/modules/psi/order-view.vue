<template>
  <el-dialog :title="descriptions.orderName+'详情'" :close-on-click-modal="false" width="70%" :visible.sync="visible">
    <el-descriptions :title="descriptions.orderName" :column="4">
      <el-descriptions-item :label="descriptions.no" :span="4">{{info.no}}<span style="color: #66b1ff" v-clipboard:copy="info.no" v-clipboard:success="onCopySuccess">(点击复制)</span></el-descriptions-item>
      <el-descriptions-item :label="descriptions.orderUid">{{info.orderUser?info.orderUser.name:'-'}}</el-descriptions-item>
      <el-descriptions-item :label="descriptions.createDate">{{info.createDate}}</el-descriptions-item>
      <el-descriptions-item :label="descriptions.expressNo">{{info.expressNo}}</el-descriptions-item>
      <el-descriptions-item :label="descriptions.ownerUid">{{info.ownerUser?info.ownerUser.realName:'-'}}</el-descriptions-item>
      <el-descriptions-item :label="descriptions.createUid">{{info.createUser?info.createUser.realName:'-'}}</el-descriptions-item>
      <el-descriptions-item :label="descriptions.orderAmount">{{info.orderAmount}}</el-descriptions-item>
      <el-descriptions-item :label="descriptions.memo" :span="3">{{info.memo}}</el-descriptions-item>
    </el-descriptions>
    <el-descriptions :title="descriptions.orderStatus" :column="4">
      <el-descriptions-item :label="descriptions.invoiceStatus">
        <el-tag v-if="info.invoiceStatus === 'UNFINISH'" size="small">未开发票</el-tag>
        <el-tag v-else-if="info.invoiceStatus === 'FINISH'" size="small">已开发票</el-tag>
      </el-descriptions-item>
      <el-descriptions-item :label="descriptions.stockStatus">
        <el-tag v-if="info.stockStatus === 'UNFINISH'" size="small">未完成</el-tag>
        <el-tag v-if="info.stockStatus === 'PARTS'" size="small">部分完成</el-tag>
        <el-tag v-if="info.stockStatus === 'FINISH'" size="small">已完成</el-tag>
      </el-descriptions-item>
      <el-descriptions-item :label="descriptions.payStatus">
        <el-tag v-if="info.payStatus === 'DEBT'" size="small">有欠款</el-tag>
        <el-tag v-if="info.payStatus === 'PAYMENT'" size="small">待收款</el-tag>
        <el-tag v-if="info.payStatus === 'FINISH'" size="small">已完成</el-tag>
      </el-descriptions-item>
      <el-descriptions-item :label="descriptions.orderStatus">
        <el-tag v-if="info.status === 'UNFINISH'" size="small">未完成</el-tag>
        <el-tag v-if="info.status === 'FINISH'" size="small">已完成</el-tag>
      </el-descriptions-item>
    </el-descriptions>
    <el-descriptions :title="descriptions.goodsId+ '明细'">
    </el-descriptions>
    <el-table border :data="dataList" style="align-content: center;align-items: center; margin-bottom: 20px" empty-text="暂无内容">
      <el-table-column prop="goodsName" header-align="center" align="center" :label="descriptions.goodsId" width="180px"/>
      <el-table-column prop="skuid" header-align="center" align="left" :label="descriptions.skuId">
        <template v-slot="scope">
        <span  v-if="scope.row.specName">
          <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
        </span>
        </template>
      </el-table-column>
      <el-table-column prop="unitName" header-align="center" align="center" :label="descriptions.unitId" width="80px"/>
      <el-table-column prop="price" header-align="center" align="center" :label="descriptions.price" width="80px"/>
      <el-table-column prop="num" header-align="center" align="center" :label="descriptions.num" width="80px"/>
      <el-table-column prop="stockNum" header-align="center" align="center" :label="descriptions.stockNum" width="80px"/>
      <el-table-column prop="total" header-align="center" align="center" :label="descriptions.subtotal" fixed="right" width="100px">
        <template v-slot="scope">
          {{scope.row.price * scope.row.num}}
        </template>
      </el-table-column>
    </el-table>
    <el-descriptions :title="descriptions.pay+'详情'">
    </el-descriptions>
    <el-table border :data="accountList" style="align-content: center;align-items: center;" empty-text="暂无内容">
      <el-table-column prop="createDate" header-align="center" align="center" :label="descriptions.payDate"/>
      <el-table-column prop="bankId" header-align="center" align="center" :label="descriptions.payAccount">
        <template v-slot="scope">
          <span>{{scope.row.bank.bankName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="amount" header-align="center" align="center" :label="descriptions.amount"/>
    </el-table>
    <div style="margin-top:20px;align-content: center;align-items: center;text-align: center;"><el-button @click="visible=false">
      {{ descriptions.cancel }}</el-button></div>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      disabled: true,
      visible: false,
      info: {
        id: '',
        no: '',
        catalog: 'BUY', // 采购单
        type: 'ORDER', // 订单
        createDate: '',
        orderUid: '',
        expressNo: '',
        createUid: '',
        ownerUid: '',
        stockStatus: '',
        invoiceStatus: '',
        payStatus: '',
        status: '',
        memo: '',
        attachmentUrls: [],
        settlementAmount: '',
        orderAmount: ''
      },
      dataList: [],
      selectVisible: false,
      accountList: []
    }
  },
  props: {
    descriptions: {
      type: Object,
      default: {}
    }
  },
  methods: {
    init (id) {
      this.visible = true
      this.dataList = []
      this.accountList = []
      this.$nextTick(() => {
        if (id) {
          this.$http({
            url: `/psi/order/info/${id}`,
            method: 'get'
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.info = data.info
              let datalist = []
              data.info.details.forEach(item => {
                datalist.push({
                  goodsId: item.goodsId,
                  goodsName: item.goodsName,
                  specName: item.specName,
                  catalogName: item.catalogName,
                  unitName: item.unitName,
                  skuId: item.skuId,
                  warehouseId: item.warehouseId,
                  num: item.num,
                  price: item.price,
                  warehouseNum: 0,
                  stockNum: item.stockNum,
                  memo: ''
                })
              })
              this.dataList = datalist
            }
          })
        }
        this.loadAccountList(id)
      })
    },
    loadAccountList (id) {
      this.$nextTick(() => {
        this.$http({
          url: `/psi/orderamount/listAll`,
          method: 'get',
          loading: false,
          params: {
            orderId: id
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.accountList = data.list
          }
        })
      })
    },
    onCopySuccess () {
      this.$message.success('已复制')
    }
  }
}
</script>
<style scoped>
</style>
