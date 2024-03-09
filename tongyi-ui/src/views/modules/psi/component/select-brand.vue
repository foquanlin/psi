<template>
  <div>
    <el-select v-model="value[field]" placeholder="品牌" clearable filterable loading-text="加载中..." :loading="loading" @focus="loadData" @change="changeHandler">
      <el-option v-for="item in brandList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      brandList: []
    }
  },
  props: {
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'brandId'
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
      if (this.brandList.length > 0) {
        return
      }
      this.loading = true
      this.$http({
        url: '/psi/brand/listAll',
        method: 'get',
        loading: false,
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.brandList = data.list
        } else {
          this.brandList = []
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
