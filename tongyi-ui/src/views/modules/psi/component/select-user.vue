<template>
  <div>
    <el-select v-model="value" :placeholder="placeholder" clearable @focus="loadUser">
      <el-option v-for="item in userList" :key="item.userId" :value="item.userId" :label="item.realName"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      userList: []
    }
  },
  props: {
    value: {
      type: String,
      default: ''
    },
    placeholder: {
      type: String,
      default: ''
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
        this.value = value
        console.log('watch.searchForm')
      }
    }
  },
  methods: {
    loadUser () {
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
      })
    }
  }
}
</script>
