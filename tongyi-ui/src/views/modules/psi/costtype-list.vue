<template>
  <div class="mod-costtype">
    <el-table border :data="dataList">
      <el-table-column prop="name" header-align="center" align="center" label="名称"/>
      <el-table-column prop="status" header-align="center" align="center" label="是否启用">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status">启用</el-tag>
          <el-tag v-else>停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="profited" header-align="center" align="center" label="是否计入利润">
        <template v-slot="scope">
          <el-tag v-if="scope.row.profited">是</el-tag>
          <el-tag v-else>否</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:costtype:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('psi:costtype:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="text" @click="addHandle"><i class="el-icon-plus"></i>增加{{this.type==='IN'?'收入':'支出'}}类型</el-button>
    <!-- 弹窗, 新增 / 修改 -->
    <costtype-edit v-if="editVisible" ref="costtypeEdit" @refreshDataList="getDataList"/>

  </div>
</template>

<script>
  import CosttypeEdit from './costtype-edit'
  export default {
    data () {
      return {
        editVisible: false,
        dataList: []
      }
    },
    props: {
      type: {
        type: String,
        default: undefined
      }
    },
    watch: {
      type: {
        immediate: true,
        handler (value) {
          this.type = value
          console.log('watch.type')
        }
      }
    },
    components: {
      CosttypeEdit
    },
    mounted () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/costtype/listAll',
          method: 'get',
          params: {
            type: this.type
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.list
          } else {
            this.dataList = []
          }
        })
      },
      // 新增 / 修改
      addHandle () {
        this.editVisible = true
        console.log('costtype', this.type)
        this.$nextTick(() => {
          this.$refs.costtypeEdit.add(this.type)
        })
      },
      editHandle (id) {
        this.editVisible = true
        console.log('costtype', this.type)
        this.$nextTick(() => {
          this.$refs.costtypeEdit.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        let ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: '/psi/costtype/delete',
            method: 'post',
            data: ids
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({ message: '操作成功', type: 'success', duration: 1500 })
              this.getDataList()
            }
          })
        })
      }
    }
  }
</script>
