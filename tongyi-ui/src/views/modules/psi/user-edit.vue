<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="50%" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" :disabled="disabled" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item label="公司名称" prop="companyName">
        <el-input v-model="dataForm.companyName" :disabled="disabled" placeholder="公司名称" clearable/>
      </el-form-item>
      <el-form-item label="联系人" prop="contacts">
        <el-input v-model="dataForm.contacts" :disabled="disabled" placeholder="联系人" clearable/>
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
        <el-input-number v-model="dataForm.weight" :disabled="disabled" placeholder="权重" clearable type="number"/>
      </el-form-item>
<!--      <el-form-item label="状态" prop="status">-->
<!--        <el-radio-group v-model="dataForm.status" :disabled="disabled">-->
<!--          <el-radio-button label="RUN">启用</el-radio-button>-->
<!--          <el-radio-button label="STOP">停用</el-radio-button>-->
<!--        </el-radio-group>-->
<!--      </el-form-item>-->
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
          contacts: '',
          phone: '',
          email: '',
          address: '',
          memo: '',
          weight: '',
          status: '',
          type: 'CUSTOMER'
        },
        dataRule: {
          name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
          status: [{required: true, message: '状态不能为空', trigger: 'blur'}],
          type: [{required: true, message: '类型不能为空', trigger: 'blur'}],
          phone: [{ required: true, message: '请输入手机号', trigger: 'change' }, { pattern: /^1[3|4|5|6|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'change' }],
          email: [{ required: false, message: '请输入邮箱', trigger: 'change' }, { pattern: /^([a-zA-Z]|[0-9])(\w|-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/, message: '请输入正确的邮箱格式', trigger: 'change' }],
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
