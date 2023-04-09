<template>
  <order-list catalog="SALE" type="ORDER" :descriptions="descriptions"/>
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
        payStatus: '收款状态',
        stockStatus: '出库状态',
        orderUid: '客户',
        createDate: '销售日期',
        payAmount: '已收款',
        nopayAmount: '未收款',
        orderName: '销售订单',
        pay: '收款',
        payAccount: '收款账户',
        payDate: '收款日期',
        warehouseId: '出库仓库',
        stockName: '出库',
        stockDate: '出库日期',
        price: '售价'
      }
    }
  },
  components: {
    OrderList
  }
}
</script>
