<template>
  <el-drawer
    :title="!dataForm.id ? (descriptions.add+descriptions.orderName) : !disabled ? (descriptions.edit+descriptions.orderName) : (descriptions.view+descriptions.orderName)"
    :close-on-click-modal="false" size="85%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="100px" @keyup.enter.native="dataFormSubmit()" style="margin-left: 10px;margin-right: 10px;">
      <el-form-item :label="descriptions.createDate" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" :placeholder="descriptions.createDate" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item :label="descriptions.orderUid" prop="orderUid">
        <select-supplier2 v-model="dataForm" field="orderUid" :type="catalog==='BUY'?'SUPPLIER':'CUSTOMER'" />
      </el-form-item>
      <el-form-item :label="descriptions.ownerUid" prop="ownerUid">
        <select-user v-model="dataForm" field="ownerUid" :placeholder="descriptions.ownerUid"/>
      </el-form-item>
      <el-form-item :label="descriptions.expressNo" prop="expressNo">
        <el-input v-model="dataForm.expressNo" :disabled="disabled" :placeholder="descriptions.expressNo" clearable/>
      </el-form-item>
      <el-form-item :label="descriptions.memo" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" :placeholder="descriptions.memo" clearable/>
      </el-form-item>
      <el-table border :data="dataList">
          <el-table-column prop="name" header-align="center" align="center" :label="descriptions.goodsId">
            <template v-slot="scope">
              <span>{{scope.row.goodsName}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="skuId" header-align="center" align="left" :label="descriptions.skuId" width="250">
            <template v-slot="scope">
            <span  v-if="scope.row.specName">
              <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
            </span>
            </template>
          </el-table-column>
          <el-table-column prop="catalog" header-align="center" align="center" :label="descriptions.catalog">
            <template v-slot="scope">
              <span>{{scope.row.catalogName}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="unitId" header-align="center" align="center" :label="descriptions.unitId">
            <template v-slot="scope">
              <span>{{scope.row.unitName}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="costPrice" header-align="center" align="center" label="进价" width="150px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.costPrice" placeholder="进价" size="mini" style="width:100%" :disabled="disabled"/>
            </template>
          </el-table-column>
          <el-table-column prop="num" header-align="center" align="center" :label="descriptions.num" width="150px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.num" :placeholder="descriptions.num" size="mini" style="width:100%" :disabled="disabled"/>
            </template>
          </el-table-column>
          <el-table-column prop="warehouseId" header-align="center" align="center" :label="descriptions.warehouseId" width="150px">
            <template v-slot="scope">
              <select-warehouse v-model="scope.row" field="warehouseId" :placeholder="descriptions.warehouseId" />
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
          <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作" v-if="!disabled">
            <template v-slot="scope">
              <el-button type="text" size="small" @click="copyRowHandle(scope.$index)" :disabled="disabled">复制</el-button>
              <el-button type="text" size="small" @click="delRowHandle(scope.$index)" :disabled="disabled">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      <el-button type="text" size="small" class="el-icon-plus" @click="appendGoods"  style="margin-bottom: 20px">增加商品</el-button>
      <el-table border :data="accountList" style="align-content: center;align-items: center;">
        <el-table-column header-align="center" align="center" :label="descriptions.payAccount">
          <template v-slot="scope">
            <select-bank v-model="scope.row" field="bankId" :placeholder="descriptions.payAccount" :disabled="disabled"/>
          </template>
        </el-table-column>
        <el-table-column header-align="center" align="center" :label="descriptions.amount">
          <template v-slot="scope">
            <el-input-number v-model="scope.row.amount" :disabled="disabled" style="width:100%"/>
          </template>
        </el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" width="150" v-if="!disabled">
          <template v-slot="scope">
            <el-button type="text" size="small" @click="delAccountHandle(scope.$index)" :disabled="disabled">{{descriptions.delete}}</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="small" class="el-icon-plus" @click="addAccountHandler">添加付款</el-button>
    </el-form>
    <el-form :inline="false" style="margin-left: 10px;">
      <el-form-item :label="descriptions.invoiceStatus" prop="invoiceStatus">
        <el-radio-group v-model="dataForm.invoiceStatus" :placeholder="descriptions.invoiceStatus" clearable  :disabled="disabled">
          <el-radio-button value="UNFINISH" label="未开发票"/>
          <el-radio-button value="FINISH" label="已开发票"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="附件" prop="attachmentUrls">
        <el-upload :disabled="disabled" :action="url" :on-success="successHandle" :file-list="dataForm.attachmentUrls" :show-file-list="false" list-type="picture-card"><i class="el-icon-plus"></i></el-upload>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button @click="visible = false">{{descriptions.cancel}}</el-button>
        <el-button v-if="!disabled" type="primary" @click="dataFormSubmit()">{{descriptions.ok}}</el-button>
      </el-form-item>
    </el-form>
    <goods-select v-if="selectVisible" ref="goodsSelect" @select="onSelect"/>
  </el-drawer>
</template>

<script>
import GoodsSelect from './goods-select'
import SelectWarehouse from './component/select-warehouse'
import SelectBank from './component/select-bank'
import SelectUser from './component/select-user'
import SelectSupplier2 from './component/select-supplier2'
export default {
  data () {
    return {
      disabled: false,
      visible: false,
      dataForm: {
        id: '',
        no: '',
        catalog: this.catalog,
        type: this.type,
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
        attachmentUrls: [],
        settlementAmount: '',
        orderAmount: ''
      },
      dataRule: {
        createDate: [{required: true, message: '采购日期不能为空', trigger: 'blur'}],
        orderUid: [{required: true, message: '供应商不能为空', trigger: 'blur'}],
        ownerUid: [{required: true, message: '负责人不能为空', trigger: 'blur'}],
        other: []
      },
      dataList: [],
      fileList: [],
      selectVisible: false,
      accountList: [],
      url: this.$http.BASE_URL + `/sys/oss/upload?token=${this.$cookie.get('token')}`
    }
  },
  props: {
    catalog: {
      type: String,
      default: undefined
    },
    type: {
      type: String,
      default: undefined
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
    GoodsSelect,
    SelectWarehouse,
    SelectBank,
    SelectUser,
    SelectSupplier2
  },
  methods: {
    init (id, disabled) {
      this.disabled = disabled
      this.dataForm.id = id || ''
      this.visible = true
      this.dataList = []
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: `/psi/order/info/${this.dataForm.id}`,
            method: 'get'
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm = data.info
              let datalist = []
              data.info.details.forEach(item => {
                datalist.push({
                  goodsId: item.goodsId,
                  goodsName: item.goodsName,
                  specName: item.specName,
                  catalogName: item.catalogName,
                  unitName: item.unitName,
                  skuId: item.skuId,
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
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm']
        .validate((valid) => {
          if (valid) {
            this.$http({
              url: `/psi/order/save`,
              method: 'post',
              data: {
                ...this.dataForm,
                dataList: this.dataList,
                accountList: this.accountList
              }
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({message: '操作成功', type: 'success', duration: 1500})
                this.visible = false
                this.$emit('refreshDataList')
              }
            })
          }
        })
    },
    loadSupplier () {
      this.$http({
        url: '/psi/supplier/listAll',
        method: 'get',
        loading: false,
        params: {
          type: this.catalog === 'BUY' ? 'SUPPLIER' : 'CUSTOMER'
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.supplierList = data.list
        } else {
          this.supplierList = []
        }
      })
    },
    appendGoods () { // 选择商品
      this.selectVisible = true
      this.$nextTick(() => {
        this.$refs.goodsSelect.init(null, this.dataList)
      })
    },
    copyRowHandle (index) { // 复制一行
      this.dataList.push(Object.assign({}, this.dataList[index]))
    },
    delRowHandle (index) { // 删除一行
      this.dataList.splice(index, 1)
    },
    addAccountHandler () {
      this.accountList.push({bankId: '', amount: 0})
    },
    delAccountHandle (index) {
      this.accountList.splice(index, 1)
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
          costPrice: 0,
          warehouseNum: 0,
          inStockNum: 0,
          memo: ''
        })
      })
      this.dataList = datalist
    },
    // 上传成功
    successHandle (response, file, fileList) {
      this.fileList = fileList
      if (response && response.code === 0) {
        this.$message({ message: response.msg, type: 'success', duration: 2000 })
        this.getDataList()
      } else {
        this.$message.error(response.msg)
      }
    }
  }
}
</script>
<style scoped>
</style>
