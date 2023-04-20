<template>
  <el-dialog title="添加商品明细" :close-on-click-modal="false" width="600px" :visible.sync="visible" append-to-body>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item :label="spec.specName" v-for="(spec,index) in goods.specList" :key="spec.specName" prop="specName">
        <el-radio-group v-model="dataForm.spec[index]" :placeholder="spec.specName" clearable>
          <el-radio-button v-for="item in spec.specValue.split(',')" :key="item" :label="item" :value="item"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="条形码" prop="barcode">
        <el-input v-model="dataForm.barcode" ref="barcode" :disabled="disabled" placeholder="条形码" clearable style="width: 200px"></el-input>
        <span style="color: #66b1ff" @click="scanFocue">扫描商品条码</span>
      </el-form-item>
      <el-form-item label="进货价格" prop="costPrice">
        <el-input-number v-model="dataForm.costPrice" :disabled="disabled" placeholder="进货价格" clearable type="number"/>
      </el-form-item>
      <el-form-item label="销售价格" prop="salePrice">
        <el-input-number v-model="dataForm.salePrice" :disabled="disabled" placeholder="销售价格" clearable type="number"/>
      </el-form-item>
<!--      <el-form-item label="仓库" prop="warehouseId">-->
<!--        <el-radio-group v-model="dataForm.warehouseId" placeholder="仓库" clearable>-->
<!--          <el-radio-button v-for="item in warehouseList" :key="item.value" :label="item.name" :value="item.id"/>-->
<!--        </el-radio-group>-->
<!--      </el-form-item>-->
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button v-if="!disabled" type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import SelectWarehouse from './component/select-warehouse'

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
          status: '',
          specName: '',
          specValue: '',
          spec: []
        },
        dataRule: {
          // warehouseId: [{required: true, message: '仓库不能为空', trigger: 'blur'}],
          goodsId: [{required: true, message: '商品不能为空', trigger: 'blur'}],
          costPrice: [{required: true, message: '参考进价不能为空', trigger: 'blur'}],
          salePrice: [{required: true, message: '参考售价不能为空', trigger: 'blur'}],
          // num: [{required: true, message: '数量不能为空', trigger: 'blur'}],
          other: []
        },
        goods: {}
      }
    },
    components: {
      SelectWarehouse
    },
    methods: {
      init (id) {
        this.dataForm.goodsId = id || ''
        this.dataForm.spec = []
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.goodsId) {
            this.$http({
              url: `/psi/goods/info/${this.dataForm.goodsId}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.goods = data.info
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
              this.dataForm.specName = this.dataForm.spec.join(':').toString()
              this.$http({
                url: `/psi/goodssku/save`,
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
      },
      scanFocue () {
        this.$nextTick(() => {
          this.$refs.barcode.focus()
        })
      }
    }
  }
</script>
