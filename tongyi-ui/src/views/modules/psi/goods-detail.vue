<template>
  <el-dialog title="商品详情" :close-on-click-modal="false" width="70%" :visible.sync="visible">
    <el-card shadow="never">
      <el-row>
        <el-col :span="6">
          <el-image fit="contain" style="width:200px" :src="goods.picUrls"></el-image>
        </el-col>
        <el-col :span="18">
        <el-descriptions :column="2" style="margin-left: 10px">
          <el-descriptions-item label="商品名称">{{goods.name}}</el-descriptions-item>
          <el-descriptions-item label="商品分类">{{goods.catalog.name}}</el-descriptions-item>
          <el-descriptions-item label="商品编码">{{goods.no}}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{goods.createDate}}</el-descriptions-item>
          <el-descriptions-item label="单位">{{goods.unit.name}}</el-descriptions-item>
          <el-descriptions-item label="库存">{{goods.warehouseNum}}</el-descriptions-item>
          <el-descriptions-item label="备注" :span="2">{{goods.memo}}</el-descriptions-item>
        </el-descriptions>
        </el-col>
      </el-row>
      <el-table border :data="goods.skuList" style="width: 100%;" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
        <el-table-column prop="specName" header-align="center" align="center" label="规格">
          <template v-slot="scope">
            <el-tag type="info" v-if="scope.row.specName" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;margin-bottom: 10px">{{item}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="barcode" header-align="center" align="center" label="条形码"/>
        <el-table-column prop="stockNum" header-align="center" align="center" label="库存"/>
        <el-table-column prop="warehouseNum" header-align="center" align="center" label="仓库库存"/>
      </el-table>
    </el-card>
<!--    <span slot="footer" class="dialog-footer">-->
<!--      <el-button @click="visible = false">关闭</el-button>-->
<!--    </span>-->
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        id: '',
        visible: false,
        goods: {}
      }
    },
    created () {
    },
    methods: {
      init (id) {
        this.id = id || ''
        this.dataList = []
        this.visible = true
        this.$nextTick(() => {
          if (this.id) {
            this.$http({
              url: `/psi/goods/info/${this.id}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.goods = data.info
              }
            })
          }
        })
      }
    }
  }
</script>
