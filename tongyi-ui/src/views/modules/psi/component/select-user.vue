<template>
  <div>
    <el-select v-model="value[field]" :placeholder="placeholder" clearable :loading="loading" @focus="loadData">
      <el-option v-for="item in userList" :key="item.userId" :value="item.userId" :label="item.realName"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      userList: []
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
      default: 'warehouseId'
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
      if (this.userList.length > 0) {
        return
      }
      this.loading = true
      this.$http({
        url: '/sys/user/queryAll',
        method: 'get',
        loading: false,
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.userList = data.list
        } else {
          this.userList = []
        }
        this.loading = false
      })
    }
  }
}
</script>
