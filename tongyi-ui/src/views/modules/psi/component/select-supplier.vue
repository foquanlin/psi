<template>
  <div>
    <el-radio-group v-model="supplierType" @change="changeSupplier">
      <el-radio-button label="CUSTOMER">客户</el-radio-button>
      <el-radio-button label="SUPPLIER">供应商</el-radio-button>
    </el-radio-group>
    <el-select v-model="value[field]" :placeholder="supplierType==='SUPPLIER'?'供应商':'客户'" clearable filterable loading-text="加载中..." :loading="loading" @focus="loadData" @change="changeHandler">
      <el-option v-for="item in supplierList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      supplierType: 'SUPPLIER',
      supplierList: []
    }
  },
  props: {
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'supplierId'
    }
  },
  watch: {
    value: {
      immediate: true,
      handler (value) {
        this.value = value
        console.log('watch.searchForm')
      }
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      if (this.supplierList.length > 0) {
        return
      }
      this.loading = true
      this.$http({
        url: '/psi/supplier/listAll',
        method: 'get',
        loading: false,
        params: {
          type: this.supplierType
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.supplierList = data.list
        } else {
          this.supplierList = []
        }
        this.loading = false
      })
    },
    changeSupplier () {
      this.value[this.field] = ''
      this.supplierList = []
      this.$emit('change')
    },
    changeHandler () {
      this.$emit('change')
    }
  }
}
</script>
