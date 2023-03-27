<template>
  <el-select v-model="searchForm.type" placeholder="出入库" clearable>
    <el-option value="IN" label="入库"></el-option>
    <el-option value="OUT" label="出库"></el-option>
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
