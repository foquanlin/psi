<template>
  <el-dialog title="盘点详情" :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-descriptions title="盘点单" :column="2">
      <el-descriptions-item label="盘点单号">{{info.no}}<span style="color: #66b1ff" v-clipboard:copy="info.no" v-clipboard:success="onCopySuccess">(点击复制)</span></el-descriptions-item>
      <el-descriptions-item label="盘点仓库">{{info.warehouse?info.warehouse.name:''}}</el-descriptions-item>
      <el-descriptions-item label="盘点日期">{{info.createDate}}</el-descriptions-item>
      <el-descriptions-item label="操作人">{{info.createUser?info.createUser.realName:''}}</el-descriptions-item>
      <el-descriptions-item label="备注" :span="2">{{info.memo}}</el-descriptions-item>
    </el-descriptions>
    <el-table border :data="info.details" style="width: 100%;">
      <el-table-column prop="name" header-align="center" align="left" label="商品">
        <template v-slot="scope">
          <span>{{scope.row.goods.name}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="warehouseId" header-align="center" align="left" label="仓库名称">
        <template v-slot="scope">
          <span>{{scope.row.warehouse.name}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="unitId" header-align="center" align="left" label="单位">
        <template v-slot="scope">
          <span>{{scope.row.goods.unit.name}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="beforeNum" header-align="center" align="right" label="盘点前数量"/>
      <el-table-column prop="afterNum" header-align="center" align="right" label="盘点数量"/>
      <el-table-column prop="afterNum" header-align="center" align="right" label="盘点差异">
        <template v-slot="scope">
          <span>{{scope.row.beforeNum - scope.row.afterNum}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="memo" header-align="center" align="left" label="备注"/>
      <el-table-column fixed="right" header-align="center" align="center" width="50" label="操作">
        <template v-slot="scope">
          <el-button type="text" size="small" @click="deleteHandle(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        id: '',
        visible: false,
        info: {}
      }
    },
    methods: {
      init (id) {
        this.id = id || ''
        this.info = {warehouse: {name: ''}, createUser: {realName: ''}}
        this.visible = true
        this.$nextTick(() => {
          if (this.id) {
            this.$http({
              url: `/psi/check/info/${this.id}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.info = data.info
              }
            })
          }
        })
      },
      onCopySuccess () {
        this.$message.success('已复制')
      },
      deleteHandle (row) {
        this.$confirm(`确定删除盘点明细里的商品<[${row.goods.name}]>吗?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: '/psi/checkdetail/delete',
            method: 'post',
            data: [row.id]
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({message: '操作成功', type: 'success', duration: 1500})
              this.getDataList()
            }
          })
        })
      }
    }
  }
</script>
