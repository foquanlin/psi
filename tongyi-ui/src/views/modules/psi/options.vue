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
    }
  }
</script>
