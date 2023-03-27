<template>
  <div>
    <el-radio-group v-model="searchForm.supplierType" @change="changeSupplier">
      <el-radio-button label="CUSTOMER">客户</el-radio-button>
      <el-radio-button label="SUPPLIER">供应商</el-radio-button>
    </el-radio-group>
    <el-select v-model="searchForm.supplierId" placeholder="客户供应商" clearable filterable loading-text="加载中..." @focus="loadSupplier">
      <el-option v-for="item in supplierList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      supplierType: '',
      supplierList: []
    }
  },
  props: {
    searchForm: {
      type: Object,
      default: {}
    }
  },
  watch: {
    searchForm: {
      immediate: true,
      handler (value) {
        this.searchForm = value
        console.log('watch.searchForm')
      }
    }
  },
  components: {
  },
  methods: {
    loadSupplier () {
      if (this.supplierList.length > 0) {
        return
      }
      this.$http({
        url: '/psi/supplier/listAll',
        method: 'get',
        loading: false,
        params: {
          type: this.searchForm.supplierType
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.supplierList = data.list
        } else {
          this.supplierList = []
        }
      })
    },
    changeSupplier () {
      this.supplierList = []
    }
  }
}
</script>
