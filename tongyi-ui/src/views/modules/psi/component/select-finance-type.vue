<template>
  <el-select v-model="value[field]" :placeholder="placeholder" clearable :loading="loading" @focus="loadData" @change="changeHandler">
    <el-option v-for="item in dataList" :key="item.id" :value="item.id" :label="item.name"/>
  </el-select>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      dataList: []
    }
  },
  props: {
    value: {
      type: Object,
      default: {}
    },
    placeholder: {
      type: String,
      default: ''
    },
    field: {
      type: String,
      default: 'typeId'
    },
    type: {
      type: String,
      default: 'IN'
    }
  },
  watch: {
    value: {
      immediate: true,
      handler (value) {
        this.value = value
        console.log('watch.searchForm')
      }
    },
    placeholder: {
      immediate: true,
      handler (value) {
        this.placeholder = value
        console.log('watch.searchForm')
      }
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      if (this.dataList.length > 0) {
        return
      }
      this.loading = true
      this.$http({
        url: '/psi/costtype/listAll',
        method: 'get',
        loading: false,
        params: {
          type: this.type
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList = data.list
        } else {
          this.dataList = []
        }
        this.loading = false
      })
    },
    changeHandler () {
      this.$emit('change')
    }
  }
}
</script>
