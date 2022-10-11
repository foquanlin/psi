<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="品牌" prop="brandId">
        <el-input v-model="dataForm.brandId" :disabled="disabled" placeholder="品牌" clearable/>
      </el-form-item>
      <el-form-item label="商品编码" prop="no">
        <el-input v-model="dataForm.no" :disabled="disabled" placeholder="商品编码" clearable/>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" :disabled="disabled" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="创建时间" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="单位" prop="unitId">
        <el-input v-model="dataForm.unitId" :disabled="disabled" placeholder="单位" clearable/>
      </el-form-item>
      <el-form-item label="图片" prop="picUrls">
        <el-input v-model="dataForm.picUrls" :disabled="disabled" placeholder="图片" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-input v-model="dataForm.status" :disabled="disabled" placeholder="状态" clearable/>
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
          brandId: '',
          no: '',
          name: '',
          createDate: '',
          unitId: '',
          picUrls: '',
          memo: '',
          status: ''},
        dataRule: {
          brandId: [{required: true, message: '品牌不能为空', trigger: 'blur'}],
          name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '创建时间不能为空', trigger: 'blur'}],
          unitId: [{required: true, message: '单位不能为空', trigger: 'blur'}],
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
              url: `/psi/goods/info/${this.dataForm.id}`,
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
                url: `/psi/goods/${!this.dataForm.id ? 'save' : 'update'}`,
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