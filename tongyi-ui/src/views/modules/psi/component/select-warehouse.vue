<template>
  <div>
    <el-radio-group  v-if="radio" v-model="value[field]" :placeholder="placeholder?placeholder:'仓库'" clearable :disabled="disabled" :loading="loading" @focus="loadData" @change="changeHandler">
      <el-radio-button v-for="item in warehouseList" :key="item.value" :label="item.id">{{item.name}}</el-radio-button>
    </el-radio-group>
    <el-select v-else v-model="value[field]" :placeholder="placeholder?placeholder:'仓库'" clearable :disabled="disabled" :multiple="multiple" :size="size" :loading="loading" @focus="loadData" @change="changeHandler">
      <el-option v-for="item in warehouseList" :key="item.id" :label="item.name" :value="item.id"/>
    </el-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      warehouseList: []
    }
  },
  props: {
    value: {
      type: Object,
      default: {}
    },
    field: {
      type: String,
      default: 'warehouseId'
    },
    multiple: {
      type: Boolean,
      default: false
    },
    placeholder: {
      type: String,
      default: ''
    },
    radio: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    },
    size: {
      type: String,
      default: ''
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      if (this.warehouseList.length > 0) {
        return
      }
      this.loading = true
      this.$http({
        url: '/psi/warehouse/listAll',
        method: 'get',
        loading: false,
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.warehouseList = data.list
        } else {
          this.warehouseList = []
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
