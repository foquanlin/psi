<template>
  <el-dialog title="调拨单详情" :close-on-click-modal="false" width="70%" :visible.sync="visible">
    <el-descriptions title="调拨单" :column="4">
      <el-descriptions-item label="调拨单号">{{info.no}}<span style="color: #66b1ff" v-clipboard:copy="info.no" v-clipboard:success="onCopySuccess">(点击复制)</span></el-descriptions-item>
      <el-descriptions-item label="调入仓库">{{info.inWarehouseName}}</el-descriptions-item>
      <el-descriptions-item label="调出仓库">{{info.outWarehouseName}}</el-descriptions-item>
      <el-descriptions-item label="日期">{{info.createDate}}</el-descriptions-item>
      <el-descriptions-item label="备注" :span="3">{{info.memo}}</el-descriptions-item>
    </el-descriptions>
    <el-descriptions title="商品明细">
    </el-descriptions>
    <el-table border :data="dataList" style="margin-bottom: 20px" empty-text="暂无内容">
      <el-table-column prop="goodsName" header-align="center" align="center" label="商品" width="180px"/>
      <el-table-column prop="specName" header-align="center" align="left" label="规格">
        <template v-slot="scope">
        <span  v-if="scope.row.specName">
          <el-tag type="info" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
        </span>
        </template>
      </el-table-column>
      <el-table-column prop="unitName" header-align="center" align="center" label="单位" width="80px"/>
      <el-table-column prop="num" header-align="center" align="center" label="调拨数量" width="80px"/>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button type="text" size="small" @click="deleteHandle(scope.row, scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top:20px;align-content: center;align-items: center;text-align: center;"><el-button @click="visible=false">取消</el-button></div>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      info: {
        id: ''
      },
      dataList: []
    }
  },
  components: {
  },
  methods: {
    init (id) {
      this.visible = true
      this.loadInfo(id)
    },
    loadInfo (id) {
      this.dataList = []
      this.$nextTick(() => {
        if (id) {
          this.$http({
            url: `/psi/allocation/info/${id}`,
            method: 'get'
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.info = data.info
              this.dataList = data.goodsList
            }
          })
        }
      })
    },
    onCopySuccess () {
      this.$message.success('已复制')
    },
    deleteHandle (row, index) {
      this.$confirm(`确定进行[删除]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/psi/allocationgoods/delete',
          method: 'post',
          data: [row.id]
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({ message: '操作成功', type: 'success', duration: 1500 })
            this.loadInfo(this.info.id)
          }
        })
      })
    }
  }
}
</script>
<style scoped>
</style>
