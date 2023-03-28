<template>
  <el-select v-model="value[field]" :disabled="!goodsId" placeholder="规格" clearable filterable loading-text="加载中..." @focus="loadData">
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
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'skuId'
    },
    goodsId: {
      type: String,
      default: ''
    },
    multiple: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    goodsId: {
      immediate: true,
      handler (value) {
        this.skuList = []
        this.loadData()
      }
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      if (this.skuList.length > 0) {
        return
      }
      this.$http({
        url: '/psi/goodssku/listAll',
        method: 'get',
        loading: false,
        params: {
          goodsId: this.goodsId
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
