<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="订单" prop="orderId">
        <el-input v-model="dataForm.orderId" :disabled="disabled" placeholder="订单" clearable/>
      </el-form-item>
      <el-form-item label="日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="日期" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-input v-model="dataForm.type" :disabled="disabled" placeholder="类型" clearable/>
      </el-form-item>
      <el-form-item label="银行账户" prop="bankId">
        <el-input v-model="dataForm.bankId" :disabled="disabled" placeholder="银行账户" clearable/>
      </el-form-item>
      <el-form-item label="金额" prop="amount">
        <el-input v-model="dataForm.amount" :disabled="disabled" placeholder="金额" clearable type="number"/>
      </el-form-item>
      <el-form-item label="操作人" prop="createUid">
        <el-input v-model="dataForm.createUid" :disabled="disabled" placeholder="操作人" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item label="附件" prop="attachmentUrls">
        <el-input v-model="dataForm.attachmentUrls" :disabled="disabled" placeholder="附件" clearable/>
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
          orderId: '',
          createDate: '',
          type: '',
          bankId: '',
          amount: '',
          createUid: '',
          memo: '',
          attachmentUrls: ''},
        dataRule: {
          orderId: [{required: true, message: '订单不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '日期不能为空', trigger: 'blur'}],
          type: [{required: true, message: '类型不能为空', trigger: 'blur'}],
          bankId: [{required: true, message: '银行账户不能为空', trigger: 'blur'}],
          amount: [{required: true, message: '金额不能为空', trigger: 'blur'}],
          createUid: [{required: true, message: '操作人不能为空', trigger: 'blur'}],
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
              url: `/psi/orderamount/info/${this.dataForm.id}`,
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
                url: `/psi/orderamount/${!this.dataForm.id ? 'save' : 'update'}`,
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