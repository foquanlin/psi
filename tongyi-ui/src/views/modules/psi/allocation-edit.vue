<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="单号" prop="no">
        <el-input v-model="dataForm.no" :disabled="disabled" placeholder="单号" clearable/>
      </el-form-item>
      <el-form-item label="出库仓库" prop="outWarehouseId">
        <el-input v-model="dataForm.outWarehouseId" :disabled="disabled" placeholder="出库仓库" clearable/>
      </el-form-item>
      <el-form-item label="入库仓库" prop="inWarehouseId">
        <el-input v-model="dataForm.inWarehouseId" :disabled="disabled" placeholder="入库仓库" clearable/>
      </el-form-item>
      <el-form-item label="创建日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="创建日期" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
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
          outWarehouseId: '',
          inWarehouseId: '',
          createDate: '',
          memo: ''},
        dataRule: {
          no: [{required: true, message: '单号不能为空', trigger: 'blur'}],
          outWarehouseId: [{required: true, message: '出库仓库不能为空', trigger: 'blur'}],
          inWarehouseId: [{required: true, message: '入库仓库不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '创建日期不能为空', trigger: 'blur'}],
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
              url: `/psi/allocation/info/${this.dataForm.id}`,
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
                url: `/psi/allocation/${!this.dataForm.id ? 'save' : 'update'}`,
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