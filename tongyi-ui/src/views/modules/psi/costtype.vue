<template>
  <div class="mod-costtype">
    <el-tabs>
      <el-tab-pane label="收入类型" >
        <costtype-list type="IN"/>
      </el-tab-pane>
      <el-tab-pane label="支出类型">
        <costtype-list type="OUT"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import CosttypeList from './costtype-list'
  export default {
    data () {
      return {
      }
    },
    components: {
      CosttypeList
    }
  }
</script>
