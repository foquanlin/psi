<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="订单编号" prop="no">
        <el-input v-model="dataForm.no" :disabled="disabled" placeholder="订单编号" clearable/>
      </el-form-item>
      <el-form-item label="订单分类" prop="catalog">
        <el-input v-model="dataForm.catalog" :disabled="disabled" placeholder="订单分类" clearable/>
      </el-form-item>
      <el-form-item label="订单类型" prop="type">
        <el-input v-model="dataForm.type" :disabled="disabled" placeholder="订单类型" clearable/>
      </el-form-item>
      <el-form-item label="订单日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="订单日期" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="客户供应商" prop="supplierId">
        <el-input v-model="dataForm.supplierId" :disabled="disabled" placeholder="客户供应商" clearable/>
      </el-form-item>
      <el-form-item label="快递单号" prop="expressNo">
        <el-input v-model="dataForm.expressNo" :disabled="disabled" placeholder="快递单号" clearable/>
      </el-form-item>
      <el-form-item label="制单人" prop="createUid">
        <el-input v-model="dataForm.createUid" :disabled="disabled" placeholder="制单人" clearable/>
      </el-form-item>
      <el-form-item label="库存状态" prop="stockStatus">
        <el-input v-model="dataForm.stockStatus" :disabled="disabled" placeholder="库存状态" clearable/>
      </el-form-item>
      <el-form-item label="发票状态" prop="invoiceStatus">
        <el-input v-model="dataForm.invoiceStatus" :disabled="disabled" placeholder="发票状态" clearable/>
      </el-form-item>
      <el-form-item label="付款状态" prop="payStatus">
        <el-input v-model="dataForm.payStatus" :disabled="disabled" placeholder="付款状态" clearable/>
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-input v-model="dataForm.status" :disabled="disabled" placeholder="订单状态" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item label="附件" prop="attachmentUrls">
        <el-input v-model="dataForm.attachmentUrls" :disabled="disabled" placeholder="附件" clearable/>
      </el-form-item>
      <el-form-item label="结算金额" prop="settlementAmount">
        <el-input v-model="dataForm.settlementAmount" :disabled="disabled" placeholder="结算金额" clearable type="number"/>
      </el-form-item>
      <el-form-item label="订单金额" prop="orderAmount">
        <el-input v-model="dataForm.orderAmount" :disabled="disabled" placeholder="订单金额" clearable type="number"/>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button v-if="!disabled" type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        disabled: false,
        visible: false,
        dataForm: {
          id: '',
          no: '',
          catalog: '',
          type: '',
          createDate: '',
          supplierId: '',
          expressNo: '',
          createUid: '',
          stockStatus: '',
          invoiceStatus: '',
          payStatus: '',
          status: '',
          memo: '',
          attachmentUrls: '',
          settlementAmount: '',
          orderAmount: ''},
        dataRule: {
          no: [{required: true, message: '订单编号不能为空', trigger: 'blur'}],
          type: [{required: true, message: '订单类型不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '订单日期不能为空', trigger: 'blur'}],
          supplierId: [{required: true, message: '客户供应商不能为空', trigger: 'blur'}],
          expressNo: [{required: true, message: '快递单号不能为空', trigger: 'blur'}],
          createUid: [{required: true, message: '制单人不能为空', trigger: 'blur'}],
          stockStatus: [{required: true, message: '库存状态不能为空', trigger: 'blur'}],
          invoiceStatus: [{required: true, message: '发票状态不能为空', trigger: 'blur'}],
          payStatus: [{required: true, message: '付款状态不能为空', trigger: 'blur'}],
          status: [{required: true, message: '订单状态不能为空', trigger: 'blur'}],
          other: []
        }
      }
    },
    methods: {
      init (id, disabled) {
        this.disabled = disabled
        this.dataForm.id = id || ''
        this.visible = true
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
                url: `/psi/order/${!this.dataForm.id ? 'save' : 'update'}`,
                method: 'post',
                data: this.dataForm
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.$message({ message: '操作成功', type: 'success', duration: 1500 })
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            }
          })
      }
    }
  }
</script>