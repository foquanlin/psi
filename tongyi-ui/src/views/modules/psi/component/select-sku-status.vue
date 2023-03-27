<template>
  <el-select v-model="searchForm.skuStatus" placeholder="上架/下架" clearable>
    <el-option value="UP" label="上架"></el-option>
    <el-option value="DOWN" label="下架"></el-option>
  </el-select>
</template>


<script>
export default {
  data () {
    return {
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
  }
}
</script>
