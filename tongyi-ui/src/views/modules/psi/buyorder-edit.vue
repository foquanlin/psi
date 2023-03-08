<template>
  <el-drawer
    :title="!dataForm.id ? '新增采购单' : !disabled ? '修改采购单' : '查看采购单'"
    :close-on-click-modal="false" size="85%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="100px" @keyup.enter.native="dataFormSubmit()" style="margin-left: 10px;margin-right: 10px;">
      <el-form-item label="订单日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="订单日期" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="供应商" prop="orderUid">
        <el-select v-model="dataForm.orderUid" placeholder="供应商" clearable :disabled="disabled">
          <el-option v-for="item in supplierList" :key="item.id" :value="item.id" :label="item.name"/>
        </el-select>
      </el-form-item>
      <el-form-item label="快递单号" prop="expressNo">
        <el-input v-model="dataForm.expressNo" :disabled="disabled" placeholder="快递单号" clearable/>
      </el-form-item>
      <el-form-item label="责任人" prop="ownerUid">
        <el-select v-model="dataForm.ownerUid" placeholder="责任人" clearable :disabled="disabled">
          <el-option v-for="item in userList" :key="item.userId" :value="item.userId" :label="item.realName"/>
        </el-select>
      </el-form-item>
<!--      <el-form-item label="制单人" prop="createUid">-->
<!--        <el-select v-model="dataForm.createUid" placeholder="商品名称" clearable :disabled="disabled">-->
<!--          <el-option v-for="item in userList" :key="item.userId" :value="item.userId" :label="item.realName"/>-->
<!--        </el-select>-->
<!--      </el-form-item>-->
      <el-form-item label="发票状态" prop="invoiceStatus">
        <el-select v-model="dataForm.invoiceStatus" placeholder="发票状态" clearable  :disabled="disabled">
          <el-option value="UNFINISH" label="未开发票"></el-option>
          <el-option value="FINISH" label="已开发票"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item label="附件" prop="attachmentUrls">
        <el-input v-model="dataForm.attachmentUrls" :disabled="disabled" placeholder="附件" clearable/>
      </el-form-item>
    </el-form>
    <el-form style="margin-left: 10px;margin-right: 10px;">
      <el-form-item>
        <el-button type="primary" @click="appendGoods">增加商品</el-button>
      </el-form-item>
      <el-table border :data="dataList" style="align-content: center;align-items: center;">
        <el-table-column prop="name" header-align="center" align="center" label="商品">
          <template v-slot="scope">
            <span>{{scope.row.goodsName}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="skuId" header-align="center" align="center" label="规格">
          <template v-slot="scope">
            <span>{{scope.row.specName}}</span>
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
        <el-table-column prop="costPrice" header-align="center" align="center" label="进价" width="250px">
          <template v-slot="scope">
            <el-input-number v-model="scope.row.costPrice" placeholder="进价" size="mini" style="width:100%"/>
          </template>
        </el-table-column>
        <el-table-column prop="num" header-align="center" align="center" label="订购数量">
          <template v-slot="scope">
            <el-input-number v-model="scope.row.num" placeholder="订购数量" size="mini" style="width:100%"/>
          </template>
        </el-table-column>
        <el-table-column prop="warehouseId" header-align="center" align="center" label="仓库">
          <template v-slot="scope">
            <el-select v-model="scope.row.warehouseId" placeholder="入库仓库" clearable>
              <el-option v-for="item in warehouseList" :key="item.value" :label="item.name" :value="item.id"/>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="inStockNum" header-align="center" align="center" label="入库数量">
          <template v-slot="scope">
            <el-input-number v-model="scope.row.inStockNum" placeholder="入库数量" size="mini" style="width:100%" :max="scope.row.num"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column prop="total" header-align="center" align="center" label="小计">
          <template v-slot="scope">
            {{scope.row.costPrice * scope.row.num}}
          </template>
        </el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
          <template v-slot="scope">
            <el-button type="text" size="small" @click="copyRowHandle(scope.$index)">复制</el-button>
            <el-button type="text" size="small" @click="delRowHandle(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-form-item style="margin-top:20px;text-align: center">
        <el-button @click="visible = false">取消</el-button>
        <el-button v-if="!disabled" type="primary" @click="dataFormSubmit()">确定</el-button>
      </el-form-item>
    </el-form>
    <goods-select v-if="selectVisible" ref="goodsSelect" @select="onSelect"/>
  </el-drawer>
</template>

<script>
import GoodsSelect from './goods-select'
export default {
  data () {
    return {
      disabled: false,
      visible: false,
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
        createDate: [{required: true, message: '采购日期不能为空', trigger: 'blur'}],
        orderUid: [{required: true, message: '供应商不能为空', trigger: 'blur'}],
        expressNo: [{required: true, message: '快递单号不能为空', trigger: 'blur'}],
        ownerUid: [{required: true, message: '负责人不能为空', trigger: 'blur'}],
        other: []
      },
      supplierList: [],
      userList: [],
      goodsList: [],
      dataList: [],
      warehouseList: [],
      selectVisible: false
    }
  },
  components: {
    GoodsSelect
  },
  mounted () {
    this.$nextTick(() => {
      this.loadSupplier()
      this.loadGoods()
      this.loadUser()
      this.loadWarehouse()
    })
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
              url: `/psi/buyorder/${!this.dataForm.id ? 'save' : 'update'}`,
              method: 'post',
              data: {
                ...this.dataForm,
                dataList: this.dataList
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
          type: 'SUPPLIER'
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.supplierList = data.list
        } else {
          this.supplierList = []
        }
      })
    },
    loadGoods () {
      this.$http({
        url: '/psi/goods/listAll',
        method: 'get',
        loading: false,
        data: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.goodsList = data.list
        } else {
          this.goodsList = []
        }
      })
    },
    loadUser () {
      this.$http({
        url: '/sys/user/queryAll',
        method: 'get',
        loading: false,
        data: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.userList = data.list
        } else {
          this.userList = []
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
    onSelect (list) { // 选中的商品
      let datalist = []
      console.log(list)
      list.forEach(item => {
        datalist.push({
          goodsId: item.goodsId,
          goodsName: item.goods.name,
          specName: item.specName,
          catalogName: item.goods.catalog.name,
          unitName: item.goods.unit.name,
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
    loadWarehouse () {
      this.$http({
        url: '/psi/warehouse/listAll',
        method: 'get',
        loading: false,
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.warehouseList = data.list
        } else {
          this.warehouseList = []
        }
      })
    }
  }
}
</script>
<style scoped>
</style>
