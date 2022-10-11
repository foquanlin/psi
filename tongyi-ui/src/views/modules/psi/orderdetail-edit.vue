<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'"
    :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="订单" prop="orderId">
        <el-input v-model="dataForm.orderId" :disabled="disabled" placeholder="订单" clearable/>
      </el-form-item>
      <el-form-item label="商品" prop="goodsId">
        <el-input v-model="dataForm.goodsId" :disabled="disabled" placeholder="商品" clearable/>
      </el-form-item>
      <el-form-item label="数量" prop="num">
        <el-input v-model="dataForm.num" :disabled="disabled" placeholder="数量" clearable type="number"/>
      </el-form-item>
      <el-form-item label="skuid" prop="skuId">
        <el-input v-model="dataForm.skuId" :disabled="disabled" placeholder="skuid" clearable/>
      </el-form-item>
      <el-form-item label="进货价" prop="price">
        <el-input v-model="dataForm.price" :disabled="disabled" placeholder="进货价" clearable type="number"/>
      </el-form-item>
      <el-form-item label="入库数量" prop="inStockNum">
        <el-input v-model="dataForm.inStockNum" :disabled="disabled" placeholder="入库数量" clearable type="number"/>
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
          orderId: '',
          goodsId: '',
          num: '',
          skuId: '',
          price: '',
          inStockNum: '',
          memo: ''},
        dataRule: {
          orderId: [{required: true, message: '订单不能为空', trigger: 'blur'}],
          goodsId: [{required: true, message: '商品不能为空', trigger: 'blur'}],
          num: [{required: true, message: '数量不能为空', trigger: 'blur'}],
          skuId: [{required: true, message: 'skuid不能为空', trigger: 'blur'}],
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
              url: `/psi/orderdetail/info/${this.dataForm.id}`,
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
                url: `/psi/orderdetail/${!this.dataForm.id ? 'save' : 'update'}`,
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