<template>
  <div class="mod-customer">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="searchForm.name" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
        <el-button v-if="isAuth(rightSave)" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="isAuth(rightDelete)" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="name" header-align="center" align="center" label="名称"/>
      <el-table-column prop="companyName" header-align="center" align="center" label="公司名称"/>
      <el-table-column prop="contacts" header-align="center" align="center" label="联系人"/>
      <el-table-column prop="phone" header-align="center" align="center" label="电话"/>
      <el-table-column prop="email" header-align="center" align="center" label="邮箱"/>
      <el-table-column prop="address" header-align="center" align="center" label="地址"/>
      <el-table-column prop="memo" header-align="center" align="center" label="备注"/>
      <el-table-column prop="weight" header-align="center" align="center" label="权重"/>
      <el-table-column prop="status" header-align="center" align="center" label="状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status ==='RUN'">启用</el-tag>
          <el-tag v-else>停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth(rightInfo)" type="text" size="small" @click="showDetails(scope.row.id)">查看</el-button>
          <el-button v-if="isAuth(rightUpdate)" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth(rightDelete)" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <customer-edit v-if="editVisible" ref="customerEdit" @refreshDataList="getDataList"/>
  </div>
</template>

<script>
  import CustomerEdit from './user-edit'
  export default {
    data () {
      return {
        searchForm: {
          name: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListSelections: [],
        editVisible: false,
        rightSave: 'psi:' + this.type.toLowerCase() + ':save',
        rightUpdate: 'psi:' + this.type.toLowerCase() + ':update',
        rightDelete: 'psi:' + this.type.toLowerCase() + ':delete',
        rightInfo: 'psi:' + this.type.toLowerCase() + ':info'
      }
    },
    props: {
      type: {
        type: String,
        default: true
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
      CustomerEdit
    },
    mounted () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/supplier/list',
          method: 'get',
          params: {
            page: this.pageIndex,
            limit: this.pageSize,
            type: this.type,
            ...this.searchForm
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.total
          } else {
            this.dataList = []
            this.totalPage = 0
          }
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 查看详情
      showDetails (id) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.customerEdit.init(id, true)
        })
      },
      // 新增 / 修改
      editHandle (id) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.customerEdit.init(id)
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
            url: '/psi/supplier/delete',
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
