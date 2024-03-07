<template>
  <div style="text-align: left;margin-left: 1px;width: 100%">
    <el-popover placement="right-start" trigger="hover" style="width: 100%;" v-if="urls && urls !== ''">
      <el-image fit="contain" style="width: 400px;" @click="openImg(urls)" :src="urls"/>
      <img slot="reference" style="height: 50px;width: 50px;" :src="urls"/>
    </el-popover>
    {{name}}
  </div>
</template>
<script>
export default {
  props: {
    name: {
      type: String,
      require: true
    },
    urls: {
      type: String,
      require: false
    }
  }
}
</script>
