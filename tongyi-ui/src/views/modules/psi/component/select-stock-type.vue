<template>
  <el-select v-model="value[field]" placeholder="出入库" clearable>
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
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'type'
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
  }
}
</script>
