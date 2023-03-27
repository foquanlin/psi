<template>
  <div>
    <el-select v-model="searchForm.bankId" placeholder="账户" clearable filterable loading-text="加载中..." @focus="loadBank">
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
  methods: {
    loadBank () {
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
