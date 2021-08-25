<template>
  <el-dialog
    title="短信配置"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="短信类型" size="mini" prop="type">
        <el-radio-group v-model="dataForm.type">
          <el-radio :label="1">创瑞云SMS</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="发送域名" prop="domain">
        <el-input v-model="dataForm.domain" placeholder="发送域名"></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="name">
        <el-input v-model="dataForm.name" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="接口密钥" prop="pwd">
        <el-input v-model="dataForm.pwd" placeholder="接口密钥"></el-input>
      </el-form-item>
      <el-form-item label="签名" prop="sign">
        <el-input v-model="dataForm.sign" placeholder="【公司简称】"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          type: 1,
          domain: '',
          name: '',
          pwd: '',
          sign: ''
        },
        dataRule: {
          domain: [
            {required: true, message: '发送域名不能为空', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '名称不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init () {
        this.visible = true
        this.$nextTick(() => {
          this.$http({
            url: `/sys/smslog/config`,
            method: 'get'
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm = data.config
            }
          })
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm']
          .validate((valid) => {
            if (valid) {
              this.$http({
                url: `/sys/smslog/saveConfig`,
                method: 'post',
                data: this.dataForm
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.$message({
                    message: '操作成功',
                    type: 'success',
                    duration: 1500
                  })
                  this.visible = false
                }
              })
            }
          })
      }
    }
  }
</script>
