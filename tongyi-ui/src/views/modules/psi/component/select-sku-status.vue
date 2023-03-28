<template>
  <el-select v-model="value[field]" placeholder="上架/下架" clearable>
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
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'skuStatus'
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
