<!--订单付款信息修改-->
<template>
  <el-dialog
    :title="!dataForm.id?'新增付款明细' :  '修改付款明细'"
    :close-on-click-modal="false" width="50%" :visible.sync="visible" append-to-body>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" placeholder="日期" clearable value-format="yyyy-MM-dd" type="date"/>
      </el-form-item>
      <el-form-item :label="付款账户" prop="bankId">
        <select-bank v-model="dataForm" field="bankId" placeholder="付款账户"/>
      </el-form-item>
      <el-form-item label="金额" prop="amount">
        <el-input-number v-model="dataForm.amount" placeholder="金额" clearable/>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import SelectBank from './component/select-bank'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: '',
          bankId: '',
          type: 'PAY',
          amount: 0,
          createDate: ''
        },
        dataRule: {
          createDate: [{required: true, message: '日期不能为空', trigger: 'blur'}],
          bankId: [{required: true, message: '付款账户不能为空', trigger: 'blur'}],
          amount: [{required: true, message: '金额不能为空', trigger: 'blur'}],
          other: []
        }
      }
    },
    components: {
      SelectBank
    },
    methods: {
      init (orderId, id) {
        this.orderId = orderId
        this.dataForm.id = id || ''
        console.log(this.dataForm.id)
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: `/psi/orderamount/info/${this.dataForm.id}`,
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
                url: `/psi/orderamount/${!this.dataForm.id ? 'save' : 'update'}`,
                method: 'post',
                data: {
                  orderId: this.orderId,
                  ...this.dataForm
                }
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
