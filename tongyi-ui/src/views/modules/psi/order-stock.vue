<!--修改订单明细-->
<template>
<div>
  <el-table border :data="dataList">
    <el-table-column prop="name" header-align="center" align="center" :label="descriptions.goodsId">
      <template v-slot="scope">
        <select-goods v-if="scope.row.append && !scope.row.goodsId" v-model="scope.row" field="goodsId" @change="selectGoods"/>
        <span v-else>{{scope.row.goodsName}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="skuId" header-align="center" align="left" :label="descriptions.skuId" width="280">
      <template v-slot="scope">
        <select-sku v-if="scope.row.append" v-model="scope.row" :goods-id="scope.row.goodsId" field="skuId"/>
        <span v-else-if="scope.row.specName">
          <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;margin-bottom: 10px">{{item}}</el-tag>
        </span>
      </template>
    </el-table-column>
    <el-table-column prop="catalogName" header-align="center" align="center" :label="descriptions.catalog"/>
    <el-table-column prop="unitName" header-align="center" align="center" :label="descriptions.unitId"/>
    <el-table-column prop="price" header-align="center" align="center" :label="priceName" width="150px"/>
    <el-table-column prop="num" header-align="center" align="center" :label="numberName+descriptions.num" width="150px"/>
    <el-table-column prop="stockNum" header-align="center" align="center" :label="stockName+descriptions.num" width="150px"/>
    <el-table-column prop="total" header-align="center" align="center" :label="descriptions.subtotal">
      <template v-slot="scope">
        {{scope.row.costPrice * scope.row.num}}
      </template>
    </el-table-column>
    <el-table-column fixed="right" header-align="center" align="center" width="150" :label="descriptions.action">
      <template v-slot="scope">
        <el-button type="text" size="small" @click="inStockHandle(scope.row, scope.$index)">{{descriptions.stockName}}</el-button>
      </template>
    </el-table-column>

  </el-table>
  <order-stock-inout v-if="inStockVisible" ref="orderStockInout" :descriptions="descriptions" :order="order" :catalog="catalog" :type="type"/>
</div>
</template>

<script>
import OrderStockInout from './order-stock-inout'
import SelectGoods from './component/select-goods'
import SelectSku from './component/select-sku'
import Options from './options'
export default {
  data () {
    return {
      disabled: true,
      inStockVisible: false,
      priceName: '',
      stockName: '',
      numberName: ''
    }
  },
  props: {
    order: {
      type: Object,
      default: true,
      require: true
    },
    dataList: {
      type: Array,
      default: true
    },
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
    order: { // 订单id
      immediate: true,
      handler (value) {
        this.order = value
      }
    },
    dataList: {
      immediate: true,
      handler (value) {
        this.dataList = value
      }
    },
    catalog: { // 订单id
      immediate: true,
      handler (value) {
        this.priceName = Options.priceName(this.catalog, this.type)
        this.numberName = Options.numberName(this.catalog, this.type)
        this.stockName = Options.stockName(this.catalog, this.type)
      }
    }
  },
  components: {
    OrderStockInout,
    SelectGoods,
    SelectSku,
    Options
  },
  methods: {
    getDataList () {
      this.$nextTick(() => {
        this.$http({
          url: `/psi/orderdetail/listAll`,
          method: 'post',
          params: {
            orderId: this.order.id
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            let datalist = []
            data.list.forEach(item => {
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
            this.dataList = datalist
          }
        })
      })
    },
    selectGoods (id, goods) {
      let item = this.dataList[this.dataList.length - 1]
      item.id = goods.id
      item.goodsId = goods.id
      item.goodsName = goods.name
      item.catalogName = goods.catalogName
      item.unitName = goods.unitName
    },
    inStockHandle (row, index) {
      this.inStockVisible = true
      this.$nextTick(() => {
        this.$refs.orderStockInout.show(row)
      })
    }
  }
}
</script>
