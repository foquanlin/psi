<template>
  <el-select v-model="searchForm.warehouseIds" placeholder="仓库" clearable :multiple="multiple">
    <el-option v-for="item in warehouseList" :key="item.id" :label="item.name" :value="item.id"/>
  </el-select>
</template>

<script>
export default {
  data () {
    return {
      warehouseList: []
    }
  },
  props: {
    searchForm: {
      type: Object,
      default: {}
    },
    multiple: {
      type: Boolean,
      default: false
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
  mounted () {
    this.loadWarehouse()
  },
  methods: {
    loadWarehouse () {
      this.$http({
        url: '/psi/warehouse/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.warehouseList = data.list
        } else {
          this.warehouseList = []
        }
      })
    }
  }
}
</script>
