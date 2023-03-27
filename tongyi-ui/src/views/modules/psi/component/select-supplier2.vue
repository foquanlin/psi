<template>
  <div>
    <el-select v-model="value" :placeholder="type==='SUPPLIER'?'供应商':'客户'" clearable filterable loading-text="加载中..." @focus="loadSupplier">
      <el-option v-for="item in supplierList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      supplierList: []
    }
  },
  props: {
    type: {
      type: String,
      default: {}
    },
    value: {
      type: Object,
      default: {}
    }
  },
  watch: {
    type: {
      immediate: true,
      handler (value) {
        this.type = value
        console.log('watch.type')
      }
    },
    value: {
      immediate: true,
      handler (value) {
        this.value = value
        console.log('watch.searchForm')
      }
    }
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
          type: this.type
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.supplierList = data.list
        } else {
          this.supplierList = []
        }
      })
    }
  }
}
</script>
