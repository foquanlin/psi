<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="客户供应商" prop="supplierId">
        <el-input v-model="dataForm.supplierId" :disabled="disabled" placeholder="客户供应商" clearable/>
      </el-form-item>
      <el-form-item label="仓库" prop="warehouseId">
        <el-input v-model="dataForm.warehouseId" :disabled="disabled" placeholder="仓库" clearable/>
      </el-form-item>
      <el-form-item label="商品" prop="goodsId">
        <el-input v-model="dataForm.goodsId" :disabled="disabled" placeholder="商品" clearable/>
      </el-form-item>
      <el-form-item label="skuid" prop="skuId">
        <el-input v-model="dataForm.skuId" :disabled="disabled" placeholder="skuid" clearable/>
      </el-form-item>
      <el-form-item label="关联单号" prop="orderId">
        <el-input v-model="dataForm.orderId" :disabled="disabled" placeholder="关联单号" clearable/>
      </el-form-item>
      <el-form-item label="出入库分类" prop="catalog">
        <el-input v-model="dataForm.catalog" :disabled="disabled" placeholder="出入库分类" clearable/>
      </el-form-item>
      <el-form-item label="出入库类型" prop="type">
        <el-input v-model="dataForm.type" :disabled="disabled" placeholder="出入库类型" clearable/>
      </el-form-item>
      <el-form-item label="数量" prop="num">
        <el-input v-model="dataForm.num" :disabled="disabled" placeholder="数量" clearable type="number"/>
      </el-form-item>
      <el-form-item label="批次" prop="batchNo">
        <el-input v-model="dataForm.batchNo" :disabled="disabled" placeholder="批次" clearable/>
      </el-form-item>
      <el-form-item label="时间" prop="createTime">
        <el-date-picker v-model="dataForm.createTime" :disabled="disabled" placeholder="时间" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-input v-model="dataForm.status" :disabled="disabled" placeholder="状态" clearable/>
      </el-form-item>
      <el-form-item label="平均进价" prop="costPrice">
        <el-input v-model="dataForm.costPrice" :disabled="disabled" placeholder="平均进价" clearable type="number"/>
      </el-form-item>
      <el-form-item label="平均售价" prop="salePrice">
        <el-input v-model="dataForm.salePrice" :disabled="disabled" placeholder="平均售价" clearable type="number"/>
      </el-form-item>
      <el-form-item label="操作人" prop="createUid">
        <el-input v-model="dataForm.createUid" :disabled="disabled" placeholder="操作人" clearable/>
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
          supplierId: '',
          warehouseId: '',
          goodsId: '',
          skuId: '',
          orderId: '',
          catalog: '',
          type: '',
          num: '',
          batchNo: '',
          createTime: '',
          status: '',
          costPrice: '',
          salePrice: '',
          createUid: ''},
        dataRule: {
          supplierId: [{required: true, message: '客户供应商不能为空', trigger: 'blur'}],
          warehouseId: [{required: true, message: '仓库不能为空', trigger: 'blur'}],
          goodsId: [{required: true, message: '商品不能为空', trigger: 'blur'}],
          skuId: [{required: true, message: 'skuid不能为空', trigger: 'blur'}],
          catalog: [{required: true, message: '出入库分类不能为空', trigger: 'blur'}],
          type: [{required: true, message: '出入库类型不能为空', trigger: 'blur'}],
          num: [{required: true, message: '数量不能为空', trigger: 'blur'}],
          createTime: [{required: true, message: '时间不能为空', trigger: 'blur'}],
          status: [{required: true, message: '状态不能为空', trigger: 'blur'}],
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
              url: `/psi/stock/info/${this.dataForm.id}`,
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
                url: `/psi/stock/${!this.dataForm.id ? 'save' : 'update'}`,
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