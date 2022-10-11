<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="仓库" prop="warehouseId">
        <el-input v-model="dataForm.warehouseId" :disabled="disabled" placeholder="仓库" clearable/>
      </el-form-item>
      <el-form-item label="商品" prop="goodsId">
        <el-input v-model="dataForm.goodsId" :disabled="disabled" placeholder="商品" clearable/>
      </el-form-item>
      <el-form-item label="商品编码" prop="no">
        <el-input v-model="dataForm.no" :disabled="disabled" placeholder="商品编码" clearable/>
      </el-form-item>
      <el-form-item label="条形码" prop="barcode">
        <el-input v-model="dataForm.barcode" :disabled="disabled" placeholder="条形码" clearable/>
      </el-form-item>
      <el-form-item label="进货价格" prop="costPrice">
        <el-input v-model="dataForm.costPrice" :disabled="disabled" placeholder="进货价格" clearable type="number"/>
      </el-form-item>
      <el-form-item label="销售价格" prop="salePrice">
        <el-input v-model="dataForm.salePrice" :disabled="disabled" placeholder="销售价格" clearable type="number"/>
      </el-form-item>
      <el-form-item label="数量" prop="num">
        <el-input v-model="dataForm.num" :disabled="disabled" placeholder="数量" clearable type="number"/>
      </el-form-item>
      <el-form-item label="规格名称" prop="specName">
        <el-input v-model="dataForm.specName" :disabled="disabled" placeholder="规格名称" clearable/>
      </el-form-item>
      <el-form-item label="规格值" prop="specValue">
        <el-input v-model="dataForm.specValue" :disabled="disabled" placeholder="规格值" clearable/>
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
          warehouseId: '',
          goodsId: '',
          no: '',
          barcode: '',
          costPrice: '',
          salePrice: '',
          num: '',
          specName: '',
          specValue: ''},
        dataRule: {
          warehouseId: [{required: true, message: '仓库不能为空', trigger: 'blur'}],
          goodsId: [{required: true, message: '商品不能为空', trigger: 'blur'}],
          num: [{required: true, message: '数量不能为空', trigger: 'blur'}],
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
              url: `/psi/goodssku/info/${this.dataForm.id}`,
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
                url: `/psi/goodssku/${!this.dataForm.id ? 'save' : 'update'}`,
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