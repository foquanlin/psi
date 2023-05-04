<script>
  export default {
    catalogName (catalog, type) {
      let title = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        title = '采购'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        title = '采购'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        title = '销售'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        title = '销售'
      }
      return title
    },
    titleName (catalog, type) {
      let title = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        title = '采购订单'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        title = '采购退单'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        title = '销售订单'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        title = '销售退单'
      }
      return title
    },
    stockName (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = '入库'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = '出库'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = '出库'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = '入库'
      }
      return name
    },
    stockCatalog (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = 'CAIGOU'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = 'CAIGOU'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = 'XIAOSHOU'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = 'XIAOSHOU'
      }
      return name
    },
    stockType (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = 'IN'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = 'OUT'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = 'OUT'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = 'IN'
      }
      return name
    },
    payName (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = '付款'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = '退款'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = '收款'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = '退款'
      }
      return name
    },
    payType (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = 'PAY'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = 'RECEIPTS'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = 'PAY'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = 'RECEIPTS'
      }
      return name
    },
    numberName (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = '采购'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = '退货'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = '销售'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = '退货'
      }
      return name
    },
    supperName (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = '供应商'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = '供应商'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = '客户'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = '客户'
      }
      return name
    },
    priceName (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = '进价'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = '进价'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = '售价'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = '售价'
      }
      return name
    },
    priceField (catalog, type) {
      let name = ''
      if (catalog === 'BUY' && type === 'ORDER') {
        name = 'costPrice'
      } else if (catalog === 'BUY' && type === 'REFUND') {
        name = 'costPrice'
      } else if (catalog === 'SALE' && type === 'ORDER') {
        name = 'salePrice'
      } else if (catalog === 'SALE' && type === 'REFUND') {
        name = 'salePrice'
      }
      return name
    },
    descriptions (catalog, type) {
      let descs = {}
      if (catalog === 'BUY' && type === 'ORDER') {
        descs = {
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
          payStatus: '付款状态',
          stockStatus: '入库状态',
          orderUid: '供应商',
          createDate: '采购日期',
          payAmount: '已付款',
          nopayAmount: '未付款',
          orderName: '采购订单',
          pay: '付款',
          payAccount: '付款账户',
          payDate: '付款日期',
          warehouseId: '入库仓库',
          stockName: '入库',
          stockNum: '入库数量',
          stockDate: '入库日期',
          price: '进价'
        }
      } else if (catalog === 'BUY' && type === 'REFUND') {
        descs = {
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

          orderStatus: '退单状态',
          payStatus: '退款状态',
          stockStatus: '出库状态',
          orderUid: '供应商',
          createDate: '退单日期',
          payAmount: '已退款',
          nopayAmount: '未退款',
          orderName: '采购退单',
          pay: '退款',
          payAccount: '退款账户',
          payDate: '退款日期',
          warehouseId: '出库仓库',
          stockName: '出库',
          stockNum: '出库数量',
          stockDate: '出库日期',
          price: '进价'
        }
      } else if (catalog === 'SALE' && type === 'ORDER') {
        descs = {
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
          stockNum: '出库数量',
          stockDate: '出库日期',
          price: '售价'
        }
      } else if (catalog === 'SALE' && type === 'REFUND') {
        descs = {
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

          orderStatus: '退单状态',
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
          stockNum: '入库数量',
          stockDate: '入库日期',
          price: '售价'
        }
      }
      return descs
    }
  }
</script>
