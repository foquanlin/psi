<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
      <el-form-item label="邮箱服务器" prop="emailHost">
        <el-input v-model="dataForm.emailHost" placeholder="邮箱服务器地址"></el-input>
      </el-form-item>
      <el-form-item label="服务器端口" prop="emailPort">
        <el-input v-model="dataForm.emailPort" placeholder="服务器端口"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="邮箱密码" prop="emailPw">
        <el-input v-model="dataForm.emailPw" placeholder="邮箱密码"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { isEmail } from '@/utils/validate'

  export default {
    data () {
      let validateEmail = (rule, value, callback) => {
        if (!isEmail(value)) {
          callback(new Error('邮箱格式错误'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        dataForm: {
          userId: '',
          emailPw: '',
          emailHost: '',
          emailPort: ''
        },
        dataRule: {
          email: [
            {required: true, message: '邮箱不能为空', trigger: 'blur'},
            {validator: validateEmail, trigger: 'blur'}
          ],
          emailHost: [{required: true, message: '邮箱服务器地址不能为空', trigger: 'blur'}],
          emailPw: [{required: true, message: '邮箱密码不能为空', trigger: 'blur'}]
        }
      }
    },
    methods: {
      init () {
        this.visible = true
        this.$nextTick(() => {
          this.$http({
            url: '/sys/user/info',
            method: 'get'
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm = data.user
            }
          })
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: `/sys/maillog/config`,
              method: 'post',
              data: {
                'userId': this.dataForm.userId,
                'email': this.dataForm.email,
                'emailHost': this.dataForm.emailHost,
                'emailPort': this.dataForm.emailPort,
                'emailPw': this.dataForm.emailPw
              }
            }).then(({ data }) => {
              if (data && data.code === 0) {
                this.visible = false
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500
                })
                this.$emit('refreshDataList')
              }
            })
          }
        })
      }
    }
  }
</script>
