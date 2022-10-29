<template>
  <el-dialog :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'" :close-on-click-modal="false" size="90%" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" style="margin-top: 20px">
      <el-form-item label="规格" prop="skuId">
        <el-select v-model="dataForm.skuId" :disabled="disabled" placeholder="规格" clearable>
          <el-option v-for="item in skuList" :key="item.id" :label="item.specName" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="仓库" prop="warehouseId">
        <el-select v-model="dataForm.warehouseId" :disabled="disabled" placeholder="仓库" clearable>
          <el-option v-for="item in warehouseList" :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="创建时间" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" clearable/>
      </el-form-item>
      <el-form-item label="数量" prop="name">
        <el-input v-model="dataForm.name" :disabled="disabled" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item label="单价" prop="costPrice">
        <el-input v-model="dataForm.costPrice" :disabled="disabled" placeholder="单价" clearable/>
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
          skuId: '',
          warehouseId: '',
          name: '',
          createDate: '',
          memo: ''
        },
        dataRule: {
          warehouseId: [{required: true, message: '分类不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '创建时间不能为空', trigger: 'blur'}],
          other: []
        },
        warehouseList: [],
        skuList: []
      }
    },
    created () {
      this.$http({
        url: '/psi/warehouse/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.warehouseList = data.list
        } else {
          this.warehouseList = []
        }
      })
    },
    methods: {
      init (id, disabled) {
        this.disabled = disabled
        this.dataForm.id = id || ''
        this.specList = []
        this.skuList = []
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: '/psi/goodssku/listAll',
              method: 'get',
              params: {
                goodsId: this.dataForm.id
              }
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.skuList = data.list
              } else {
                this.skuList = []
              }
            })
            this.$http({
              url: `/psi/goods/info/${this.dataForm.id}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm = data.info
                if (this.dataForm.specList) {
                  this.dataForm.specList.forEach(item => {
                    item.specValue = item.specValue.split(',')
                  })
                }
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
              this.dataForm.specList.forEach(item => {
                item.specValue = item.specValue.join(',')
              })
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
