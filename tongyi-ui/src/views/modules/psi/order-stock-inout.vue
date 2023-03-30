<template>
  <el-dialog :title="descriptions.orderName+descriptions.stockName" :close-on-click-modal="false" width="70%" :visible.sync="visible" append-to-body>
    <el-row>
      <el-col :span="3">
        <el-image fit="contain" style="width:100px" :src="sku.picUrls"></el-image>
      </el-col>
      <el-col :span="21">
      <el-descriptions :column="1">
        <el-descriptions-item :label="descriptions.goodsId+ '名称'">{{sku.goodsName}}</el-descriptions-item>
        <el-descriptions-item :label="descriptions.skuId">{{sku.specName}}</el-descriptions-item>
        <el-descriptions-item label="进货价">{{sku.costPrice}}</el-descriptions-item>
        <el-descriptions-item label="销售价">{{sku.salePrice}}</el-descriptions-item>
      </el-descriptions>
      </el-col>
    </el-row>
    <el-table border :data="dataList">
      <el-table-column prop="createTime" header-align="center" align="left" :label="descriptions.stockDate" width="150">
        <template v-slot="scope">
          <el-date-picker v-if="scope.row.edited" v-model="scope.row.createTime" type="date" value-format="yyyy-MM-dd" size="mini" :placeholder="descriptions.stockDate" style="width: 100%"/>
          <span v-else>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="type" header-align="center" align="center" label="类型">
        <template v-slot="scope">
          <span v-if="scope.row.type === 'IN'">入库</span>
          <span v-else>出库</span>
        </template>
      </el-table-column>
      <el-table-column prop="warehouseId" header-align="center" align="center" :label="descriptions.warehouseId" width="150">
        <template v-slot="scope">
          <select-warehouse v-if="scope.row.edited" v-model="scope.row" field="warehouseId" size="mini" :placeholder="descriptions.warehouseId"/>
          <span v-else>{{scope.row.warehouseName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="unitId" header-align="center" align="center" :label="descriptions.unitId">
        <template v-slot="scope">
          {{scope.row.unitName}}
        </template>
      </el-table-column>
      <el-table-column prop="num" header-align="center" align="center" :label="descriptions.num">
        <template v-slot="scope">
          <el-input v-if="scope.row.edited" v-model="scope.row.num" size="mini"/>
          <span v-else>{{scope.row.num}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="createUid" header-align="center" align="center" :label="descriptions.createUid">
        <template v-slot="scope">
          {{scope.row.createName}}
        </template>
      </el-table-column>
      <el-table-column prop="memo" header-align="center" align="center" :label="descriptions.memo">
        <template v-slot="scope">
          <el-input v-if="scope.row.edited" v-model="scope.row.memo" size="mini"/>
          <span v-else>{{scope.row.memo}}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" :label="descriptions.action">
        <template v-slot="scope">
          <el-button type="text" size="small" @click="editHandle(scope.row, scope.$index)" v-if="scope.row.id && !scope.row.edited">
            {{ descriptions.edit }}</el-button>
          <el-button type="text" size="small" @click="saveHandle(scope.row, scope.$index)" v-if="scope.row.edited">{{ descriptions.save }}</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row, scope.$index)">{{descriptions.delete}}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">{{descriptions.cancel}}</el-button>
      <el-button type="primary" @click="addRow">{{descriptions.stockName}}</el-button>
    </span>
  </el-dialog>
</template>

<script>
import Options from './options'
import SelectWarehouse from './component/select-warehouse'
export default {
  data () {
    return {
      visible: false,
      sku: {goods: {picUrl: ''}},
      dataList: [],
      goodsId: '',
      skuId: '',
      title: '',
      stockCatalog: '',
      stockType: ''
    }
  },
  props: {
    order: {
      type: Object,
      require: true
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
    catalog: { // 订单分类: 采购单,销售单
      immediate: true,
      handler (value) {
        this.catalog = value
        this.title = Options.titleName(this.catalog, this.type)
        this.stockCatalog = Options.stockCatalog(this.catalog, this.type)
        this.stockType = Options.stockType(this.catalog, this.type)
        console.log('watch.catalog', value)
        this.getDataList()
      }
    },
    type: { // 订单类型: 订单, 退单
      immediate: true,
      handler (value) {
        this.getDataList()
      }
    },
    order: {
      immediate: true,
      handler (value) {
        this.order = value
        this.getDataList()
      }
    }
  },
  components: {
    SelectWarehouse,
    Options
  },
  methods: {
    show (goodsId, skuId) {
      this.goodsId = goodsId
      this.skuId = skuId
      this.visible = true
      this.dataList = []
      this.loadGoods()
      this.getDataList()
    },
    loadGoods () {
      this.$nextTick(() => {
        this.$http({
          url: `/psi/goodssku/info/${this.skuId}`,
          method: 'get',
          loading: false
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.sku = data.info
          }
        })
      })
    },
    getDataList () {
      this.$nextTick(() => {
        this.$http({
          url: '/psi/stock/listAll',
          method: 'get',
          loading: false,
          params: {
            orderId: this.order.id,
            goodsId: this.goodsId,
            skuId: this.skuId,
            catalog: this.stockCatalog,
            type: this.stockType
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            let datalist = data.list
            datalist.forEach(item => {
              item.edited = false
            })
            this.dataList = datalist
          } else {
            this.dataList = []
          }
        })
      })
    },
    addRow () {
      let news = false
      this.dataList.forEach(item => {
        if (item.append) {
          news = true
        }
      })
      if (news) {
        this.$message({ message: '请完成当前数据录入', type: 'warning', duration: 1500 })
        return
      }
      this.dataList.push({
        edited: true,
        supplierId: this.order.orderUid,
        catalog: this.stockCatalog,
        type: this.stockType,
        goods: this.sku.goods,
        createTime: ''
      })
    },
    editHandle (row, index) {
      this.dataList[index].edited = true
      this.$forceUpdate()
    },
    deleteHandle (row, index) {
      if (!row.id) {
        this.dataList.splice(index, 1)
        return
      }
      this.$confirm(`确定进行[删除]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/psi/order/deleteStock',
          method: 'post',
          data: [row.id]
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({ message: '操作成功', type: 'success', duration: 1500 })
            this.getDataList()
          }
        })
      })
    },
    saveHandle (row, index) {
      this.$http({
        url: `/psi/stock/${!row.id ? 'save' : 'update'}`,
        method: 'post',
        data: {
          supplierId: this.order.orderUid,
          catalog: this.stockCatalog,
          type: this.stockType,
          goodsId: this.goodsId,
          skuId: this.skuId,
          orderId: this.order.id,
          ...row
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message({ message: '操作成功', type: 'success', duration: 1500 })
          // this.dataList[index] = data.info
          // this.dataList[index].edited = false
          this.getDataList()
        }
      })
    }
  }
}
</script>
