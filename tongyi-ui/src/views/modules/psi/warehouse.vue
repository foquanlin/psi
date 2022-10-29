<template>
  <div class="mod-warehouse">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="searchForm.name" placeholder="名称" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1
        getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:warehouse:save')" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="isAuth('psi:warehouse:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="name" header-align="center" align="center" label="名称"/>
      <el-table-column prop="defaulted" header-align="center" align="center" label="默认仓库">
        <template v-slot="scope">
          <el-tag type="success" v-if="scope.row.defaulted">默认仓库</el-tag>
          <span v-else-if="isAuth('psi:warehouse:default')">
          <el-popover placement="top-start" title="提示" width="200" trigger="hover" content="点击,设为默认。">
            <el-tag slot="reference" @click="defaultHandler(scope.row)">设为默认</el-tag>
          </el-popover>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="location" header-align="center" align="center" label="位置"/>
      <el-table-column prop="capacity" header-align="center" align="center" label="容量"/>
      <el-table-column prop="status" header-align="center" align="center" label="状态">
        <template v-slot="scope">
          <span v-if="isAuth('psi:warehouse:status')">
            <el-popover v-if="scope.row.status == 'RUN'" placement="top-start" title="提示" width="200" trigger="hover" content="点击,停用仓库。">
              <el-tag  slot="reference" type="success" @click="statusHandler(scope.row)">启用</el-tag>
            </el-popover>
            <el-popover v-else placement="top-start" title="提示" width="200" trigger="hover" content="点击,启用仓库。">
              <el-tag slot="reference" @click="statusHandler(scope.row)">停用</el-tag>
            </el-popover>
          </span>
          <span v-else>
          <el-tag type="success" v-if="scope.row.status == 'RUN'">启用</el-tag>
          <el-tag v-else>停用</el-tag>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="description" header-align="center" align="center" label="说明"/>
      <el-table-column prop="master" header-align="center" align="center" label="负责人"/>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:warehouse:info')" type="text" size="small" @click="showDetails(scope.row.id)">查看</el-button>
          <el-button v-if="isAuth('psi:warehouse:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('psi:warehouse:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <warehouse-edit v-if="editVisible" ref="warehouseEdit" @refreshDataList="getDataList"/>
  </div>
</template>

<script>
  import warehouseEdit from './warehouse-edit'
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
      warehouseEdit,
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
          url: '/psi/warehouse/list',
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
          this.$refs.warehouseEdit.init(id, true)
        })
      },
      // 新增 / 修改
      editHandle (id) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.warehouseEdit.init(id)
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
            url: '/psi/warehouse/delete',
            method: 'post',
            data: ids
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({ message: '操作成功', type: 'success', duration: 1500 })
              this.getDataList()
            }
          })
        })
      },
      defaultHandler (row) {
        this.$http({
          url: '/psi/warehouse/default',
          method: 'get',
          params: {
            id: row.id
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({ message: '操作成功', type: 'success', duration: 1500 })
            this.getDataList()
          }
        })
      },
      statusHandler (row) {
        this.$confirm(`确定对[${row.name}]进行[${row.status === 'RUN' ? '停用' : '启用'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: '/psi/warehouse/status',
            method: 'get',
            params: {
              id: row.id,
              status: row.status === 'RUN' ? 'STOP' : 'RUN'
            }
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
