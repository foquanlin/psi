<template>
  <el-drawer
    :title="!dataForm.id ? '新增调拨单' : !disabled ? '修改调拨单' : '查看调拨单'"
    :close-on-click-modal="false" size="80%" :visible.sync="visible">
    <el-form :model="dataForm"  :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="单号" prop="no">
        <el-input v-model="dataForm.no" :disabled="disabled" placeholder="单号" clearable/>
      </el-form-item>
      <el-form-item label="调出仓库" prop="outWarehouseId">
        <el-radio-group v-model="dataForm.outWarehouseId" placeholder="调出仓库" clearable :disabled="disabled">
          <el-radio-button v-for="item in warehouseList" :key="item.value" :label="item.name" :value="item.id"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="调入仓库" prop="inWarehouseId">
        <el-radio-group v-model="dataForm.inWarehouseId" placeholder="调入仓库" clearable :disabled="disabled">
          <el-radio-button v-for="item in warehouseList" :key="item.value" :label="item.name" :value="item.id"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" :disabled="disabled" placeholder="创建日期" type="date" value-format="yyyy-MM-dd" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item style="margin-top:20px;text-align: center">
        <el-button @click="visible = false">取消</el-button>
        <el-button v-if="!disabled" type="primary" @click="dataFormSubmit()">确定</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
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
          memo: ''
        },
        warehouseList: [],
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
