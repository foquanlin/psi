<!--修改发票状态-->
<template>
  <div>
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="100px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item :label="descriptions.createDate" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :placeholder="descriptions.createDate" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item :label="descriptions.orderUid" prop="orderUid">
        <select-supplier2 v-model="dataForm" field="orderUid" :type="catalog==='BUY'?'SUPPLIER':'CUSTOMER'" />
      </el-form-item>
      <el-form-item :label="descriptions.expressNo" prop="expressNo">
        <el-input v-model="dataForm.expressNo" :placeholder="descriptions.expressNo" clearable/>
      </el-form-item>
      <el-form-item :label="descriptions.ownerUid" prop="ownerUid">
        <select-user v-model="dataForm" field="ownerUid" :placeholder="descriptions.ownerUid"/>
      </el-form-item>
      <el-form-item :label="descriptions.memo" prop="memo">
        <el-input v-model="dataForm.memo" :placeholder="descriptions.memo" clearable/>
      </el-form-item>
      <el-form>
        <el-table border :data="dataList" style="align-content: center;align-items: center;" >
          <el-table-column prop="name" header-align="center" align="center" :label="descriptions.goodsId" width="250">
            <template v-slot="scope">
              <select-goods v-if="scope.row.append && !scope.row.goodsId" v-model="scope.row" field="goodsId" size="mini" @change="selectGoods"/>
              <span v-else>{{scope.row.goodsName}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="skuId" header-align="center" align="left" :label="descriptions.skuId" width="250">
            <template v-slot="scope">
              <select-sku v-if="scope.row.append" v-model="scope.row" :goods-id="scope.row.goodsId" field="skuId" size="mini"/>
              <span  v-else="scope.row.specName">
                <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="unitId" header-align="center" align="center" :label="descriptions.unitId" width="80">
            <template v-slot="scope">
              <span>{{scope.row.unitName}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="price" header-align="center" align="center" :label="priceName" width="150px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.price" :placeholder="priceName" size="mini" style="width:100%"/>
            </template>
          </el-table-column>
          <el-table-column prop="num" header-align="center" align="center" :label="numberName+descriptions.num" width="150px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.num" :placeholder="numberName+descriptions.num" size="mini" style="width:100%"/>
            </template>
          </el-table-column>
          <el-table-column prop="total" header-align="center" align="center" :label="descriptions.subtotal">
            <template v-slot="scope">
              {{scope.row.price * scope.row.num}}
            </template>
          </el-table-column>
          <el-table-column fixed="right" header-align="center" align="center" width="80" :label="descriptions.action">
            <template v-slot="scope">
              <el-button type="text" size="small" @click="delRowHandle(scope.$index)">{{ descriptions.delete }}</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="text" @click="appendGoods"><i class="el-icon-plus"></i>{{descriptions.add+descriptions.goodsId}}</el-button>
      </el-form>
      <el-form-item style="margin-top:20px;text-align: center">
        <el-button type="primary" @click="dataFormSubmit()">{{ descriptions.edit }}</el-button>
      </el-form-item>
    </el-form>
    <goods-select v-if="selectVisible" ref="goodsSelect" @select="onSelect"/>
  </div>
</template>

<script>
import GoodsSelect from './goods-select'
import SelectWarehouse from './component/select-warehouse'
import SelectSupplier2 from './component/select-supplier2'
import SelectUser from './component/select-user'
import SelectGoods from './component/select-goods'
import SelectSku from './component/select-sku'
import Options from './options'
export default {
  data () {
    return {
      dataForm: {
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
        attachmentUrls: '',
        settlementAmount: '',
        orderAmount: ''
      },
      dataRule: {
        type: [{required: true, message: '订单类型不能为空', trigger: 'blur'}],
        createDate: [{required: true, message: this.descriptions.createDate + '不能为空', trigger: 'blur'}],
        orderUid: [{required: true, message: (this.descriptions.orderUid + '不能为空'), trigger: 'blur'}],
        expressNo: [{required: true, message: this.descriptions.expressNo + '不能为空', trigger: 'blur'}],
        ownerUid: [{required: true, message: this.descriptions.ownerUid + '不能为空', trigger: 'blur'}],
        other: []
      },
      selectVisible: false,
      deleteList: [],
      numberName: '',
      priceName: ''
    }
  },
  components: {
    GoodsSelect,
    SelectWarehouse,
    SelectSupplier2,
    SelectUser,
    SelectGoods,
    SelectSku
  },
  props: {
    order: {
      type: Object,
      default: true
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
    order: {
      immediate: true,
      handler (value) {
        this.order = value
        this.dataForm = this.order
      }
    },
    dataList: {
      immediate: true,
      handler (value) {
        this.data = value
      }
    },
    catalog: {
      immediate: true,
      handler (value) {
        this.numberName = Options.numberName(this.catalog, this.type)
        this.priceName = Options.priceName(this.catalog, this.type)
        console.log('watch.catalog', value)
      }
    },
    type: {
      immediate: true,
      handler (value) {
        this.type = value
      }
    }
  },
  comments: {
    Options
  },
  methods: {
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm']
        .validate((valid) => {
          if (valid) {
            this.$http({
              url: `/psi/order/update`,
              method: 'post',
              data: {
                ...this.dataForm,
                dataList: this.dataList,
                deleteList: this.deleteList
              }
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({message: '操作成功', type: 'success', duration: 1500})
                this.$emit('refreshDataList')
              }
            })
          }
        })
    },
    appendGoods () {
      this.dataList.push(
        {
          id: '',
          append: true,
          goodsId: '',
          goodsName: '',
          specName: '',
          catalogName: '',
          unitName: '',
          skuId: '',
          warehouseId: '',
          num: 0,
          price: 0,
          warehouseNum: 0,
          inStockNum: 0,
          memo: ''
        }
      )
    },

    onSelect (list) { // 选中的商品
      let datalist = []
      list.forEach(item => {
        datalist.push({
          goodsId: item.goodsId,
          goodsName: item.goodsName,
          specName: item.specName,
          catalogName: item.catalogName,
          unitName: item.unitName,
          skuId: item.id,
          warehouseId: '',
          num: 0,
          price: 0,
          warehouseNum: 0,
          inStockNum: 0,
          memo: ''
        })
      })
      this.dataList = datalist
    },
    selectGoods (id, goods) {
      let item = this.dataList[this.dataList.length - 1]
      item.goodsId = goods.id
      item.goodsName = goods.name
      item.catalogName = goods.catalogName
      item.unitName = goods.unitName
    },
    selectSku (sku) {
      console.log(sku)
    },
    delRowHandle (index) { // 删除一行
      if (this.dataList[index].id) {
        this.dataList[index].deleted = true
        this.deleteList.push(this.dataList[index])
      }
      this.dataList.splice(index, 1)
    }
  }
}
</script>
