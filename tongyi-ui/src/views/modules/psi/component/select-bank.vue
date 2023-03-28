<template>
  <div>
    <el-select v-model="value[field]" placeholder="账户" clearable filterable loading-text="加载中..." @focus="loadData">
      <el-option v-for="item in bankList" :key="item.id" :label="item.bankName" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      bankList: []
    }
  },
  props: {
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'bankId'
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
      if (this.bankList.length > 0) {
        return
      }
      this.$http({
        url: '/psi/bank/listAll',
        method: 'get',
        loading: false,
        params: {
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.bankList = data.list
        } else {
          this.bankList = []
        }
      })
    }
  }
}
</script>
