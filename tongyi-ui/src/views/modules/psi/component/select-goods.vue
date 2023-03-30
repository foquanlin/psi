<template>
  <el-select v-model="value[field]" placeholder="商品名称" clearable filterable loading-text="加载中..." :size="size" @focus="loadData" @change="changeGoods">
    <el-option v-for="item in goodsList" :key="item.id" :label="item.name" :value="item.id"/>
  </el-select>
</template>

<script>
export default {
  data () {
    return {
      goodsList: []
    }
  },
  props: {
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'goodsId'
    },
    multiple: {
      type: Boolean,
      default: false
    },
    size: {
      type: String,
      default: ''
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
      if (this.goodsList.length > 0) {
        return
      }
      this.$http({
        url: '/psi/goods/listAll',
        method: 'get',
        loading: false,
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.goodsList = data.list
        } else {
          this.goodsList = []
        }
      })
    },
    changeGoods (value) {
      let goods = {}
      this.goodsList.forEach(item => {
        if (item.id === value) {
          goods = item
        }
      })
      this.value[this.field] = value
      this.skuList = []
      this.$emit('change', value, goods)
    }
  }
}
</script>
