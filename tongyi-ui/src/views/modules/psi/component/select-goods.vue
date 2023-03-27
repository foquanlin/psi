<template>
  <el-select v-model="searchForm.goodsId" placeholder="商品名称" clearable filterable loading-text="加载中..." @focus="loadGoods" @change="changeGoods">
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
    this.loadGoods()
  },
  methods: {
    loadGoods () {
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
    changeGoods () {
      this.searchForm.skuId = undefined
      this.skuList = []
      this.$emit('change')
    }
  }
}
</script>
