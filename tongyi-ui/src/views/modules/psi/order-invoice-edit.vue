<!--修改发票状态-->
<template>
  <el-form>
    <el-form-item>
      <el-radio-group v-model="invoiceStatus" placeholder="发票状态" clearable :disabled="!edited">
        <el-radio-button label="UNFINISH">未开发票</el-radio-button>
        <el-radio-button label="FINISH">已开发票</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" v-if="edited === false" @click="modifyHandle">修改</el-button>
      <el-button type="primary" v-if="edited === true" @click="dataFormSubmit()">保存</el-button>
      <el-button v-if="edited === true" @click="cancelHandle">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data () {
    return {
      invoiceStatus: this.status,
      edited: false
    }
  },
  props: {
    orderId: {
      type: String,
      default: true
    },
    status: {
      type: String,
      default: true
    }
  },
  watch: {
    orderId: {
      immediate: true,
      handler (value) {
        this.orderId = value
        console.log('watch.orderId', value)
      }
    },
    status: {
      immediate: true,
      handler (value) {
        this.status = value
        this.invoiceStatus = this.status
        console.log('watch.invoiceStatus', value)
      }
    }
  },
  destroyed () {
    this.edited = false
  },
  methods: {
    // 表单提交
    dataFormSubmit () {
      this.$http({
        url: `/psi/order/invoiceStatus`,
        method: 'post',
        params: {
          id: this.orderId,
          invoiceStatus: this.invoiceStatus
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message({ message: '操作成功', type: 'success', duration: 1500 })
          this.edited = false
        }
      })
    },
    cancelHandle () {
      this.edited = false
    },
    modifyHandle () {
      this.edited = true
    }
  }
}
</script>
