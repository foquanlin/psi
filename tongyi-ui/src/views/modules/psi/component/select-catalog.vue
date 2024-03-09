<template>
  <div>
    <el-select v-model="value[field]" placeholder="分类" clearable filterable loading-text="加载中..." :loading="loading" @focus="loadData" @change="changeHandler">
      <el-option v-for="item in catalogList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      catalogList: []
    }
  },
  props: {
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'catalogId'
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
      if (this.catalogList.length > 0) {
        return
      }
      this.loading = true
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
        this.loading = false
      })
    },
    changeHandler () {
      this.$emit('change')
    }
  }
}
</script>
