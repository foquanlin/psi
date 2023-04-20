<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="客户供应商" prop="supplierId">
        <el-input v-model="dataForm.supplierId" :disabled="disabled" placeholder="客户供应商" clearable/>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-input v-model="dataForm.type" :disabled="disabled" placeholder="类型" clearable/>
      </el-form-item>
      <el-form-item label="银行账户" prop="bankId">
        <el-input v-model="dataForm.bankId" :disabled="disabled" placeholder="银行账户" clearable/>
      </el-form-item>
      <el-form-item label="订单编号" prop="no">
        <el-input v-model="dataForm.no" :disabled="disabled" placeholder="订单编号" clearable/>
      </el-form-item>
      <el-form-item label="金额" prop="amount">
        <el-input v-model="dataForm.amount" :disabled="disabled" placeholder="金额" clearable type="number"/>
      </el-form-item>
      <el-form-item label="日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="日期" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="制单人" prop="createUid">
        <el-input v-model="dataForm.createUid" :disabled="disabled" placeholder="制单人" clearable/>
      </el-form-item>
      <el-form-item label="负责人" prop="ownerUid">
        <el-input v-model="dataForm.ownerUid" :disabled="disabled" placeholder="负责人" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item label="附件" prop="attachUrls">
        <el-input v-model="dataForm.attachUrls" :disabled="disabled" placeholder="附件" clearable/>
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
          supplierId: '',
          type: '',
          bankId: '',
          no: '',
          amount: '',
          createDate: '',
          createUid: '',
          ownerUid: '',
          memo: '',
          attachUrls: ''},
        dataRule: {
          supplierId: [{required: true, message: '客户供应商不能为空', trigger: 'blur'}],
          type: [{required: true, message: '类型不能为空', trigger: 'blur'}],
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
              url: `/psi/bankbill/info/${this.dataForm.id}`,
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
                url: `/psi/bankbill/${!this.dataForm.id ? 'save' : 'update'}`,
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