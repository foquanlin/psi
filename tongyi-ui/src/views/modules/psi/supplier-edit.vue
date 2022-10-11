<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" :disabled="disabled" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item label="公司名称" prop="companyName">
        <el-input v-model="dataForm.companyName" :disabled="disabled" placeholder="公司名称" clearable/>
      </el-form-item>
      <el-form-item label="联系人" prop="concat">
        <el-input v-model="dataForm.concat" :disabled="disabled" placeholder="联系人" clearable/>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="dataForm.phone" :disabled="disabled" placeholder="电话" clearable/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" :disabled="disabled" placeholder="邮箱" clearable/>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="dataForm.address" :disabled="disabled" placeholder="地址" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item label="权重" prop="weight">
        <el-input v-model="dataForm.weight" :disabled="disabled" placeholder="权重" clearable type="number"/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-input v-model="dataForm.status" :disabled="disabled" placeholder="状态" clearable/>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-input v-model="dataForm.type" :disabled="disabled" placeholder="类型" clearable/>
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
          name: '',
          companyName: '',
          concat: '',
          phone: '',
          email: '',
          address: '',
          memo: '',
          weight: '',
          status: '',
          type: ''},
        dataRule: {
          name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
          status: [{required: true, message: '状态不能为空', trigger: 'blur'}],
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
              url: `/psi/supplier/info/${this.dataForm.id}`,
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
                url: `/psi/supplier/${!this.dataForm.id ? 'save' : 'update'}`,
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