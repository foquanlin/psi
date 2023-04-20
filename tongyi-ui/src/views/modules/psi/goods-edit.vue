<template>
  <el-drawer :title="!dataForm.id ? '修改商品' : '修改商品'" :close-on-click-modal="false" size="90%" :visible.sync="visible">
    <el-row :gutter="24">
      <el-col :span="18"><div class="grid-content bg-purple-light">&nbsp;</div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      </div>
      </el-col>
    </el-row>
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" style="margin-left: 10px;margin-right: 10px;margin-top: 20px">
      <el-form-item label="品牌" prop="brandId">
        <select-brand  v-model="dataForm" field="brandId" placeholder="品牌"/>
      </el-form-item>
      <el-form-item label="分类" prop="catalogId">
        <select-catalog v-model="dataForm" field="catalogId" placeholder="分类"/>
      </el-form-item>
      <el-form-item label="商品编码" prop="no">
        <el-input v-model="dataForm.no" placeholder="商品编码" clearable/>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item label="单位" prop="unitId">
        <select-unit  v-model="dataForm" field="unitId" placeholder="单位"/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" placeholder="备注" clearable/>
      </el-form-item>
      <el-divider></el-divider>
      <el-form-item label="图片" prop="picUrls">
        <el-img v-model="dataForm.picUrls" placeholder="图片" clearable/>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<script>
  import SelectWarehouse from './component/select-warehouse'
  import SelectBrand from './component/select-brand'
  import SelectCatalog from './component/select-catalog'
  import SelectUnit from './component/select-unit'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: '',
          brandId: '',
          catalogId: '',
          no: '',
          name: '',
          createDate: '',
          unitId: '',
          picUrls: '',
          memo: '',
          status: ''
        },
        dataRule: {
          brandId: [{required: true, message: '品牌不能为空', trigger: 'blur'}],
          catalogId: [{required: true, message: '分类不能为空', trigger: 'blur'}],
          name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '创建时间不能为空', trigger: 'blur'}],
          unitId: [{required: true, message: '单位不能为空', trigger: 'blur'}],
          other: []
        }
      }
    },
    components: {
      SelectWarehouse,
      SelectBrand,
      SelectCatalog,
      SelectUnit
    },
    methods: {
      init (id) {
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
      },
      addSpec () {
        this.dataForm.specList.push({
          id: '',
          specName: '',
          specValue: ''
        })
      },
      delSpec (index) {
        this.dataForm.specList.splice(index, 1)
        this.changeSpecValue([])
      },
      saveSpec (index) {
      },
      changeSpecValue (val) {
        this.dataForm.skuList = []
        if (this.dataForm.specList.length === 0) {
          this.dataForm.skuList.push({
            id: '',
            specName: '无规格',
            barcode: '',
            costPrice: 0,
            salePrice: 0,
            num: 0,
            status: ''
          })
        } else if (this.dataForm.specList.length === 1) {
          this.dataForm.specList[0].specValue.forEach(item => {
            this.addSku({ specName: (item) })
          })
        } else {
          this.dataForm.specList[0].specValue.forEach(item => {
            this.getSpecValue(0, item)
          })
        }
      },
      getSpecValue (depth, name) {
        depth = depth + 1
        if (depth === this.dataForm.specList.length - 1) {
          this.dataForm.specList[depth].specValue.forEach(item => {
            console.log('---' + depth + '--------->' + (name + ':' + item))
            this.addSku({ specName: (name + ':' + item) })
          })
        } else {
          this.dataForm.specList[depth].specValue.forEach(item => {
            this.getSpecValue(depth, (name + ':' + item))
          })
        }
      },
      inStock () {
      },
      addSku (item) {
        console.log('addSku', item)
        console.log('skuList', this.dataForm.skuList)
        this.dataForm.skuList.push(item)
      },
      delSku (index) {
        this.dataForm.skuList.splice(index, 1)
      }
    }
  }
</script>
