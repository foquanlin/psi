<template>
  <el-dialog title="采购单详情" :close-on-click-modal="false" width="70%" :visible.sync="visible">
    <el-descriptions title="采购单" :column="4">
      <el-descriptions-item label="采购单号">{{info.no}}<span style="color: #66b1ff" v-clipboard:copy="info.no" v-clipboard:success="onCopySuccess">(点击复制)</span></el-descriptions-item>
      <el-descriptions-item label="供应商">{{info.orderUser?info.orderUser.name:'-'}}</el-descriptions-item>
      <el-descriptions-item label="订单日期">{{info.createDate}}</el-descriptions-item>
      <el-descriptions-item label="快递单号">{{info.expressNo}}</el-descriptions-item>
      <el-descriptions-item label="责任人">{{info.ownerUser?info.ownerUser.realName:'-'}}</el-descriptions-item>
      <el-descriptions-item label="制单人">{{info.createUser?info.createUser.realName:'-'}}</el-descriptions-item>
      <el-descriptions-item label="备注" :span="3">{{info.memo}}</el-descriptions-item>
    </el-descriptions>
    <el-descriptions title="订单状态" :column="4">
      <el-descriptions-item label="发票状态">
        <el-tag v-if="info.invoiceStatus === 'UNFINISH'" size="small">未开发票</el-tag>
        <el-tag v-else-if="info.invoiceStatus === 'FINISH'" size="small">已开发票</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="库存状态">
        <el-tag v-if="info.stockStatus === 'UNFINISH'" size="small">未完成</el-tag>
        <el-tag v-if="info.stockStatus === 'PARTS'" size="small">部分完成</el-tag>
        <el-tag v-if="info.stockStatus === 'FINISH'" size="small">已完成</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="付款状态">
        <el-tag v-if="info.payStatus === 'DEBT'" size="small">有欠款</el-tag>
        <el-tag v-if="info.payStatus === 'PAYMENT'" size="small">待收款</el-tag>
        <el-tag v-if="info.payStatus === 'FINISH'" size="small">已完成</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="订单状态">
        <el-tag v-if="info.status === 'UNFINISH'" size="small">未完成</el-tag>
        <el-tag v-if="info.status === 'FINISH'" size="small">已完成</el-tag>
      </el-descriptions-item>
    </el-descriptions>
    <el-descriptions title="商品明细">
    </el-descriptions>
    <el-table border :data="dataList" style="align-content: center;align-items: center; margin-bottom: 20px" empty-text="暂无内容">
      <el-table-column prop="goodsName" header-align="center" align="center" label="商品" width="180px"/>
      <el-table-column prop="skuid" header-align="center" align="left" label="规格">
        <template v-slot="scope">
        <span  v-if="scope.row.specName">
          <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
        </span>
        </template>
      </el-table-column>
      <el-table-column prop="unitName" header-align="center" align="center" label="单位" width="80px"/>
      <el-table-column prop="costPrice" header-align="center" align="center" label="进价" width="80px"/>
      <el-table-column prop="num" header-align="center" align="center" label="订购数量" width="80px"/>
      <el-table-column prop="inStockNum" header-align="center" align="center" label="入库数量" width="80px"/>
      <el-table-column prop="total" header-align="center" align="center" label="小计" fixed="right" width="100px">
        <template v-slot="scope">
          {{scope.row.costPrice * scope.row.num}}
        </template>
      </el-table-column>
    </el-table>
    <el-descriptions title="付款详情">
    </el-descriptions>
    <el-table border :data="accountList" style="align-content: center;align-items: center;" empty-text="暂无内容">
      <el-table-column prop="bankId" header-align="center" align="center" label="账户">
        <template v-slot="scope">
          <span>{{scope.row.bank.bankName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="amount" header-align="center" align="center" label="金额"/>
    </el-table>
    <div style="margin-top:20px;align-content: center;align-items: center;text-align: center;"><el-button @click="visible=false">取消</el-button></div>
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
      accountList: [{bankId: '', amount: 0}]
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
                  costPrice: item.price,
                  warehouseNum: 0,
                  inStockNum: item.inStockNum,
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
          method: 'post',
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
