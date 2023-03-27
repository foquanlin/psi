<template>
  <el-select v-model="searchForm.catalog" placeholder="出入类型" clearable>
    <el-option value="TIAOZHENG" label="库存调整"></el-option>
    <el-option value="DIAOBO" label="库存调拨"></el-option>
    <el-option value="PANDIAN" label="库存盘点"></el-option>
    <el-option value="CAIGOU" label="采购"></el-option>
    <el-option value="DINGDAN" label="订单"></el-option>
    <el-option value="XIAOSHOU" label="销售"></el-option>
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
