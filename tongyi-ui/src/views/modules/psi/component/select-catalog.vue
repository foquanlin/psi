<template>
  <div>
    <el-select v-model="searchForm.catalogId" placeholder="分类" clearable filterable loading-text="加载中..." @focus="loadCatalog">
      <el-option v-for="item in catalogList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      catalogList: []
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
    loadCatalog () {
      if (this.catalogList.length > 0) {
        return
      }
      this.$http({
        url: '/psi/catalog/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.catalogList = data.list
        } else {
          this.catalogList = []
        }
      })
    }
  }
}
</script>
