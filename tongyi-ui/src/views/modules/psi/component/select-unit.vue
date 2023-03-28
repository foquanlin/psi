<template>
  <div>
    <el-select v-model="value[field]" placeholder="单位" clearable filterable loading-text="加载中..." @focus="loadData">
      <el-option v-for="item in unitList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      unitList: []
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
      if (this.unitList.length > 0) {
        return
      }
      this.$http({
        url: '/psi/unit/listAll',
        method: 'get',
        loading: false,
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.unitList = data.list
        } else {
          this.unitList = []
        }
      })
    }
  }
}
</script>
