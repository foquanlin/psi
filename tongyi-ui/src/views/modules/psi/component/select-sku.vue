<template>
  <el-select v-model="searchForm.skuId" :disabled="!searchForm.goodsId" placeholder="规格" clearable filterable loading-text="加载中..." @focus="loadSku">
    <el-option v-for="item in skuList" :key="item.id" :label="item.specName" :value="item.id"/>
  </el-select>
</template>

<script>
export default {
  data () {
    return {
      skuList: []
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
    'searchForm.goodsId': {
      immediate: true,
      handler (value) {
        this.skuList = []
        console.log('watch.searchForm.goodsId')
        this.loadSku()
      }
    }
  },
  mounted () {
    this.loadSku()
  },
  methods: {
    loadSku () {
      if (this.skuList.length > 0) {
        return
      }
      this.$http({
        url: '/psi/goodssku/listAll',
        method: 'get',
        loading: false,
        params: {
          goodsId: this.searchForm.goodsId
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.skuList = data.list
        } else {
          this.skuList = []
        }
      })
    }
  }
}
</script>
