<template>
  <el-dialog
    :title="(dataForm.type==='IN'?'收入':'支出') + '类型'"
    :close-on-click-modal="false" width="50%" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item label="是否启用" prop="status">
        <el-switch v-model="dataForm.status" placeholder="是否启用" active-color="#13ce66" inactive-color="#ff4949" active-text="是" inactive-text="否"/>
      </el-form-item>
      <el-form-item label="是否计入利润" prop="profited">
        <el-switch v-model="dataForm.profited" placeholder="是否计入利润" active-color="#13ce66" inactive-color="#ff4949" active-text="是" inactive-text="否"/>
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
          id: '',
          type: '',
          name: '',
          status: true,
          profited: true},
        dataRule: {
          type: [{required: true, message: '收支类型不能为空', trigger: 'blur'}],
          other: []
        }
      }
    },
    methods: {
      add (type) {
        this.dataForm.id = undefined
        this.dataForm.type = type
        this.dataForm.status = true
        this.dataForm.profited = true
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      },
      init (id) {
        this.dataForm.id = id || ''
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: `/psi/costtype/info/${this.dataForm.id}`,
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
                url: `/psi/costtype/${!this.dataForm.id ? 'save' : 'update'}`,
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
