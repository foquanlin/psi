<!--修改订单明细-->
<template>
<div>
  <el-table border :data="dataList" style="align-content: center;align-items: center;">
    <el-table-column prop="name" header-align="center" align="center" label="商品">
      <template v-slot="scope">
        <el-select v-if="scope.row.append && !scope.row.goodsId" v-model="scope.row.goodsId" @change="selectGoods">
          <el-option v-for="goods in goodsList" :key="goods.id" :value="goods" :label="goods.name"/>
        </el-select>
        <span v-else>{{scope.row.goodsName}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="skuId" header-align="center" align="left" label="规格" width="280">
      <template v-slot="scope">
        <el-select v-if="scope.row.append" v-model="scope.row.skuId" @change="selectSku" @visible-change="loadSku(scope.row)">
          <el-option v-for="sku in skuList" :key="sku.id" :value="sku.id" :label="sku.specName"/>
        </el-select>
        <span v-else-if="scope.row.specName">
          <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;margin-bottom: 10px">{{item}}</el-tag>
        </span>
      </template>
    </el-table-column>
    <el-table-column prop="catalog" header-align="center" align="center" label="分类">
      <template v-slot="scope">
        <span>{{scope.row.catalogName}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="unitId" header-align="center" align="center" label="单位">
      <template v-slot="scope">
        <span>{{scope.row.unitName}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="costPrice" header-align="center" align="center" label="进价" width="150px">
      <template v-slot="scope">
        <el-input-number v-model="scope.row.costPrice" placeholder="进价" size="mini" style="width:100%" :disabled="disabled"/>
      </template>
    </el-table-column>
    <el-table-column prop="num" header-align="center" align="center" label="订购数量" width="150px">
      <template v-slot="scope">
        <el-input-number v-model="scope.row.num" placeholder="订购数量" size="mini" style="width:100%" :disabled="disabled"/>
      </template>
    </el-table-column>
    <el-table-column prop="inStockNum" header-align="center" align="center" label="入库数量" width="150px">
      <template v-slot="scope">
        <el-input-number v-model="scope.row.inStockNum" placeholder="入库数量" size="mini" style="width:100%" :max="scope.row.num" :disabled="disabled"/>
      </template>
    </el-table-column>
    <el-table-column prop="total" header-align="center" align="center" label="小计">
      <template v-slot="scope">
        {{scope.row.costPrice * scope.row.num}}
      </template>
    </el-table-column>
    <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
      <template v-slot="scope">
        <el-button type="text" size="small" @click="inStockHandle(scope.$index)">入库</el-button>
      </template>
    </el-table-column>

  </el-table>
</div>
</template>

<script>
export default {
  data () {
    return {
      disabled: true,
      goodsList: [],
      skuList: [],
      dataList: []
    }
  },
  props: {
    orderId: {
      type: String,
      default: true,
      require: true
    }
  },
  watch: {
    orderId: {
      immediate: true,
      handler (value) {
        this.orderId = value
        this.getDataList()
      }
    }
  },
  mounted () {
    this.loadGoods()
  },
  activated () {
    this.getDataList()
  },
  destroyed () {
    this.dataList = []
  },
  methods: {
    loadGoods () {
      this.$nextTick(() => {
        this.$http({
          url: '/psi/goods/listAll',
          method: 'get',
          loading: false,
          params: {}
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.goodsList = data.list
          } else {
            this.goodsList = []
          }
        })
      })
    },
    getDataList () {
      this.$nextTick(() => {
        this.$http({
          url: `/psi/orderdetail/listAll`,
          method: 'post',
          params: {
            orderId: this.orderId
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            let datalist = []
            data.list.forEach(item => {
              datalist.push({
                id: item.id,
                goodsId: item.goodsId,
                goodsName: item.goods.name,
                specName: item.sku.specName,
                catalogName: item.goods.catalog.name,
                unitName: item.goods.unit.name,
                skuId: item.sku.id,
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
      })
    },
    selectGoods (goods) {
      let item = this.dataList[this.dataList.length - 1]
      item.id = goods.id
      item.goodsId = goods.id
      item.goodsName = goods.name
      item.catalogName = goods.catalog.name
      item.unitName = goods.unit.name
      this.loadSku(goods)
    },
    selectSku (sku) {
      console.log(sku)
    },
    loadSku (row) {
      this.skuList = []
      this.$http({
        url: '/psi/goodssku/listAll',
        method: 'get',
        loading: false,
        params: {
          goodsId: row.id
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          let list = []
          data.list.forEach(item => {
            list.push(item)
          })
          this.skuList = list
        } else {
          this.skuList = []
        }
      })
    }
  }
}
</script>
