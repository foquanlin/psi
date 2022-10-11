<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="编号" prop="no">
        <el-input v-model="dataForm.no" :disabled="disabled" placeholder="编号" clearable/>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" :disabled="disabled" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item label="日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="日期" type="date" value-format="yyyy-MM-dd" clearable/>
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
          name: '',
          createDate: ''},
        dataRule: {
          no: [{required: true, message: '编号不能为空', trigger: 'blur'}],
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
              url: `/psi/batch/info/${this.dataForm.id}`,
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
                url: `/psi/batch/${!this.dataForm.id ? 'save' : 'update'}`,
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