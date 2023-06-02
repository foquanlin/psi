<template>
  <el-dialog
    :title="(type==='IN'?'收入':'支出') + '类型'"
    :close-on-click-modal="false" width="50%" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item :label="(type==='IN'?'收入':'支出') +'类型'" prop="typeId">
        <select-finance-type v-model="dataForm" field="typeId" type="IN" :placeholder="(type==='IN'?'收入':'支出') +'类型'"/>
      </el-form-item>
      <el-form-item label="日期" prop="createDate">
        <el-date-picker v-model="dataForm.createDate" placeholder="日期" clearable value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item :label="type==='IN'?'收款方':'付款方'" prop="orderUid">
        <select-supplier v-model="dataForm" field="orderUid" type="CUSTOMER"/>
      </el-form-item>
      <el-form-item label="账户" prop="bankId">
        <SelectBank v-model="dataForm" field="bankId" placeholder="账户"></SelectBank>
      </el-form-item>
      <el-form-item label="金额" prop="amount">
        <el-input-number v-model="dataForm.amount" placeholder="金额"/>
      </el-form-item>
      <el-form-item label="负责人" prop="ownerUid">
        <SelectUser v-model="dataForm" field="ownerUid" placeholder="负责人"></SelectUser>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" placeholder="备注"/>
      </el-form-item>
      <el-form-item label="附件" prop="attchment_urls">
        <el-upload :action="url" :on-success="successHandle" :file-list="dataForm.attachmentUrls" :show-file-list="false" list-type="picture-card"><i class="el-icon-plus"></i></el-upload>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import SelectFinanceType from './component/select-finance-type'
import SelectBank from './component/select-bank'
import SelectUser from './component/select-user'
import SelectSupplier from './component/select-supplier'
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        id: '',
        typeId: '',
        createDate: '',
        orderUid: '',
        bankId: '',
        amount: '',
        ownerUid: '',
        memo: '',
        attchment_urls: ''
      },
      url: this.$http.BASE_URL + `/sys/oss/upload?token=${this.$cookie.get('token')}`,
      dataRule: {
        typeId: [{required: true, message: '收支类型不能为空', trigger: 'blur'}],
        bankId: [{required: true, message: '账户不能为空', trigger: 'blur'}],
        amount: [{required: true, message: '金额不能为空', trigger: 'blur'}],
        other: []
      }
    }
  },
  props: {
    type: {
      type: String,
      default: undefined
    }
  },
  watch: {
    type: {
      immediate: true,
      handler (value) {
        this.type = value
      }
    }
  },
  components: {
    SelectFinanceType,
    SelectBank,
    SelectUser,
    SelectSupplier
  },
  methods: {
    init (id) {
      this.dataForm.id = id || ''
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: `/psi/finance/info/${this.dataForm.id}`,
            method: 'get'
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm = data.info
            }
          })
        }
      })
    },
    // 上传成功
    successHandle (response, file, fileList) {
      this.fileList = fileList
      if (response && response.code === 0) {
        this.$message({ message: response.msg, type: 'success', duration: 2000 })
        this.getDataList()
      } else {
        this.$message.error(response.msg)
      }
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm']
        .validate((valid) => {
          if (valid) {
            this.$http({
              url: `/psi/finance/${!this.dataForm.id ? 'save' : 'update'}`,
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
