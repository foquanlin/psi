<template>
  <div class="mod-brand">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="searchForm.name" placeholder="品牌名称" clearable suffix-icon="el-icon-search"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1
        getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:brand:save')" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="isAuth('psi:brand:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="name" header-align="center" align="center" label="名称"/>
      <el-table-column prop="picUrls" header-align="center" align="center" label="图片">
        <template v-slot="scope">
          <img style="height: 50%;width: 50%" @click="openImg(scope.row.listPicUrl)" :src="scope.row.picUrls"/>
        </template>
      </el-table-column>
      <el-table-column prop="status" header-align="center" align="center" label="状态"/>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:brand:info')" type="text" size="small" @click="showDetails(scope.row.id)">查看</el-button>
          <el-button v-if="isAuth('psi:brand:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('psi:brand:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <brand-edit v-if="editVisible" ref="brandEdit" @refreshDataList="getDataList"/>
  </div>
</template>

<script>
  import brandEdit from './brand-edit'
  import Options from '../sys/options'
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
        editVisible: false
      }
    },
    components: {
      brandEdit,
      Options
    },
    activated () {
      this.getDataList()
    },
    methods: {
      formatBool: (row, column, cellValue, index) => Options.formatArray(Options.yesno, cellValue),
      formatSex: (row, column, cellValue, index) => Options.formatArray(Options.genders, cellValue),
      formatDate: (row, column, cellValue, index) => Options.formatDate(row, column, cellValue, index),
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/brand/list',
          method: 'get',
          params: {
            page: this.pageIndex,
            limit: this.pageSize,
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
          this.$refs.brandEdit.init(id, true)
        })
      },
      // 新增 / 修改
      editHandle (id) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.brandEdit.init(id)
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
            url: '/psi/brand/delete',
            method: 'post',
            data: ids
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({ message: '操作成功', type: 'success', duration: 1500 })
              this.getDataList()
            }
          })
        }).catch(() => {
        })
      }
    }
  }
</script>
