<template>
  <el-dialog :title="catalogName + typeName" :close-on-click-modal="false" width="70%" :visible.sync="visible" append-to-body>
    <el-row>
      <el-col :span="3">
        <el-image fit="contain" style="width:100px" :src="sku.picUrls"></el-image>
      </el-col>
      <el-col :span="21">
      <el-descriptions :column="1">
        <el-descriptions-item label="商品名称">{{sku.goodsName}}</el-descriptions-item>
        <el-descriptions-item label="规格">{{sku.specName}}</el-descriptions-item>
        <el-descriptions-item label="进货价">{{sku.costPrice}}</el-descriptions-item>
        <el-descriptions-item label="销售价">{{sku.salePrice}}</el-descriptions-item>
      </el-descriptions>
      </el-col>
    </el-row>
    <el-table border :data="dataList">
      <el-table-column prop="createTime" header-align="center" align="left" label="日期" width="150">
        <template v-slot="scope">
          <el-date-picker v-if="scope.row.edited" v-model="scope.row.createTime" type="date" value-format="yyyy-MM-dd" size="mini"/>
          <span v-else>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="type" header-align="center" align="center" label="类型">
        <template v-slot="scope">
          <span v-if="scope.row.type === 'IN'">入库</span>
          <span v-else>出库</span>
        </template>
      </el-table-column>
      <el-table-column prop="warehouseId" header-align="center" align="center" label="仓库" width="150">
        <template v-slot="scope">
          <select-warehouse v-if="scope.row.edited" v-model="scope.row" field="warehouseId"/>
          <span v-else>{{scope.row.warehouseName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="unitId" header-align="center" align="center" label="单位">
        <template v-slot="scope">
          {{scope.row.unitName}}
        </template>
      </el-table-column>
      <el-table-column prop="num" header-align="center" align="center" label="数量">
        <template v-slot="scope">
          <el-input v-if="scope.row.edited" v-model="scope.row.num" size="mini"/>
          <span v-else>{{scope.row.num}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="createUid" header-align="center" align="center" label="操作人">
        <template v-slot="scope">
          {{scope.row.createName}}
        </template>
      </el-table-column>
      <el-table-column prop="memo" header-align="center" align="center" label="备注">
        <template v-slot="scope">
          <el-input v-if="scope.row.edited" v-model="scope.row.memo" size="mini"/>
          <span v-else>{{scope.row.memo}}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" label="操作">
        <template v-slot="scope">
          <el-button type="text" size="small" @click="editHandle(scope.row, scope.$index)" v-if="scope.row.id && !scope.row.edited">修改</el-button>
          <el-button type="text" size="small" @click="saveHandle(scope.row)" v-if="scope.row.edited">保存</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row, scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="addRow">{{typeName}}</el-button>
    </span>
  </el-dialog>
</template>

<script>
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
      typeName: '',
      catalogName: ''
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
    }
  },
  watch: {
    catalog: { // 订单分类: 采购单,销售单
      immediate: true,
      handler (value) {
        if (value === 'CAIGOU') {
          this.catalogName = '采购单'
        } else if (value === 'TIAOZHENG') {
          this.catalogName = '库存调整'
        } else if (value === 'DIAOBO') {
          this.catalogName = '库存调拨'
        } else if (value === 'PANDIAN') {
          this.catalogName = '库存盘点'
        } else if (value === 'CAIGOU') {
          this.catalogName = '采购单'
        } else if (value === 'XIAOSHOU') {
          this.catalogName = '采购单'
        }
        this.getDataList()
      }
    },
    type: { // 订单类型: 订单, 退单
      immediate: true,
      handler (value) {
        if (value === 'IN') {
          this.typeName = '入库'
        } else {
          this.typeName = '出库'
        }
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
    SelectWarehouse
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
            type: 'IN'
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
        type: this.type,
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
          url: '/psi/stock/delete',
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
    saveHandle (row) {
      this.$http({
        url: `/psi/stock/${!row.id ? 'save' : 'update'}`,
        method: 'post',
        data: {
          catalog: this.catalog,
          type: this.type,
          goodsId: this.goodsId,
          skuId: this.skuId,
          orderId: this.order.id,
          ...row
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message({ message: '操作成功', type: 'success', duration: 1500 })
          row.edited = false
        }
      })
    }
  }
}
</script>
