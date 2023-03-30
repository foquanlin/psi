<!--修改发票状态-->
<template>
  <el-form>
    <el-form-item>
      <el-radio-group v-model="order.invoiceStatus" :placeholder="descriptions.invoiceStatus" clearable :disabled="!edited">
        <el-radio-button label="UNFINISH">未开发票</el-radio-button>
        <el-radio-button label="FINISH">已开发票</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" v-if="edited === false" @click="modifyHandle">{{ descriptions.edit }}</el-button>
      <el-button type="primary" v-if="edited === true" @click="saveHandle()">{{ descriptions.save }}</el-button>
      <el-button v-if="edited === true" @click="cancelHandle">{{ descriptions.cancel }}</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data () {
    return {
      edited: false
    }
  },
  props: {
    order: {
      type: Object,
      default: true
    },
    descriptions: {
      type: Object,
      default: {}
    }
  },
  watch: {
    order: {
      immediate: true,
      handler (value) {
        this.order = value
        console.log('watch.order')
      }
    }
  },
  methods: {
    // 表单提交
    saveHandle () {
      this.$http({
        url: `/psi/order/invoiceStatus`,
        method: 'post',
        params: {
          id: this.order.id,
          invoiceStatus: this.order.invoiceStatus
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
