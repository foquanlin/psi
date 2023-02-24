<template>
  <el-dialog title="发送短信" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="发送时间" prop="stime">
        <el-date-picker v-model="dataForm.stime" type="datetime" placeholder="发送时间，填写时已填写的时间发送，不填时为当前时间发送"/>
      </el-form-item>
      <el-form-item label="发送内容" prop="content">
        <el-input type="textarea" v-model="dataForm.content" placeholder="发送内容（1-500 个汉字）UTF-8编码"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" prop="mobile">
        <el-input type="textarea" v-model="dataForm.mobile" placeholder="手机号码。多个以英文逗号隔开"></el-input>
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
          stime: '',
          content: '',
          mobile: ''
        },
        dataRule: {
          content: [
            {required: true, message: '发送内容不能为空', trigger: 'blur'}
          ],
          mobile: [
            {required: true, message: '手机号码不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init () {
        this.visible = true
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm']
          .validate((valid) => {
            if (valid) {
              this.$http({
                url: `/sys/smslog/sendSms`,
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
                  this.$emit('refreshDataList')
                }
              })
            }
          })
      }
    }
  }
</script>
