<template>
  <order-list catalog="SALE" type="REFUND" :descriptions="descriptions"/>
</template>

<script>
import OrderList from './order-list'
export default {
  data () {
    return {
      descriptions: {
        no: '订单编号',
        createDateStart: '开始日期',
        createDateEnd: '结束日期',
        createUid: '制单人',
        ownerUid: '责任人',
        invoiceStatus: '发票状态',
        memo: '备注',
        goodsId: '商品',
        orderAmount: '订单总价',
        subtotal: '合计',
        total: '合计',
        add: '新增',
        edit: '修改',
        view: '查看',
        delete: '删除',
        cancel: '取消',
        ok: '确定',
        save: '保存',
        action: '操作',
        expressNo: '快递单号',
        skuId: '规格',
        catalog: '分类',
        unitId: '单位',
        amount: '金额',
        num: '数量',

        orderStatus: '订单状态',
        payStatus: '退款状态',
        stockStatus: '入库状态',
        orderUid: '客户',
        createDate: '退单日期',
        payAmount: '已退款',
        nopayAmount: '未退款',
        orderName: '销售退单',
        pay: '退款',
        payAccount: '退款账户',
        payDate: '退款日期',
        warehouseId: '入库仓库',
        stockName: '入库',
        stockDate: '入库日期'
      }
    }
  },
  components: {
    OrderList
  }
}
</script>
