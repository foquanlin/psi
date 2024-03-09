<!--订单修改-->
<template>
  <el-drawer :title="descriptions.edit+descriptions.orderName" :close-on-click-modal="false" size="85%" :visible.sync="visible" :before-close="onClose">
      <el-tabs style="margin-left: 10px;margin-right: 10px" v-model="defaultTab" :before-leave="onChangeTab">
        <el-tab-pane :label="descriptions.orderName+ '详情'" name="detail">
          <order-detail-edit :order="order" :data-list="detailList" :descriptions="descriptions" :catalog="catalog" :type="type" ref="orderDetailEdit" @refreshDataList="loadDetails"/>
        </el-tab-pane>
        <el-tab-pane :label="descriptions.stockName" name="stock">
          <order-stock :order="order" :data-list="detailList" :descriptions="descriptions" :catalog="catalog" :type="type" ref="orderStock" @refreshDataList="loadDetails"/>
        </el-tab-pane>
        <el-tab-pane :label="descriptions.pay" name="pay">
          <order-pay-edit :order="order" :data-list="accountList" :descriptions="descriptions" :catalog="catalog" :type="type" ref="orderPayEdit" @refreshDataList="loadAccountList"/>
        </el-tab-pane>
        <el-tab-pane label="发票" name="invoice">
          <order-invoice-edit :order="order" :descriptions="descriptions" :catalog="catalog" :type="type" ref="orderInvoiceEdit" @refreshDataList="loadDetails"/>
        </el-tab-pane>
      </el-tabs>
  </el-drawer>
</template>

<script>
import OrderInvoiceEdit from './order-invoice-edit'
import OrderPayEdit from './order-pay-edit'
import OrderStock from './order-stock'
import OrderDetailEdit from './order-detail-edit'
import Options from './options'
export default {
  data () {
    return {
      visible: false,
      defaultTab: 'detail',
      order: {
        id: ''
      },
      accountList: [],
      detailList: []
    }
  },
  props: {
    catalog: {
      type: String,
      require: true
    },
    type: {
      type: String,
      require: true
    },
    descriptions: {
      type: Object,
      default: {}
    }
  },
  watch: {
    catalog: {
      immediate: true,
      handler (value) {
        this.catalog = value
        console.log('watch.catalog')
      }
    },
    type: {
      immediate: true,
      handler (value) {
        this.type = value
        console.log('watch.type')
      }
    }
  },
  components: {
    OrderInvoiceEdit,
    OrderPayEdit,
    OrderStock,
    OrderDetailEdit,
    Options
  },
  methods: {
    init (id) {
      this.order.id = id || ''
      this.accountList = []
      this.detailList = []
      this.visible = true
      this.defaultTab = 'detail'
      this.loadDetails()
      this.loadAccountList()
    },
    loadDetails () {
      this.$nextTick(() => {
        if (this.order.id) {
          this.$http({
            url: `/psi/order/info/${this.order.id}`,
            loading: false,
            method: 'get'
          }).then(({data}) => {
            if (data && data.code === 0) {
              let datalist = []
              data.info.details.forEach(item => {
                datalist.push({
                  id: item.id,
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
              this.detailList = datalist
              this.order = data.info
            }
          })
        }
      })
    },
    loadAccountList () {
      this.$nextTick(() => {
        this.$http({
          url: `/psi/orderamount/listAll`,
          method: 'get',
          params: {
            orderId: this.order.id
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            data.list.forEach(item => {
              item.edited = false
            })
            this.accountList = data.list
          }
        })
      })
    },
    onClose () {
      this.visible = false
      this.$emit('refreshDataList')
    },
    onChangeTab (newName, current) {
      // console.log('changeTab', newName, current, this.$refs.orderDetailEdit.edited, this.$refs.orderStock.edited, this.$refs.orderPayEdit.edited(), this.$refs.orderInvoiceEdit.edited)
      if (this.$refs.orderDetailEdit.edited || this.$refs.orderStock.edited || this.$refs.orderPayEdit.edited() || this.$refs.orderInvoiceEdit.edited) {
        this.$message({ message: '请先提交已修改的内容', type: 'error', duration: 1500 })
        return false
      }
      return true
    }
  }
}
</script>
<style scoped>
</style>
