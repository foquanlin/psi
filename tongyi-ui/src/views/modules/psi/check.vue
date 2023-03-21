<template>
  <div class="mod-check">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="searchForm.no" placeholder="单号" clearable/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.warehouseId" placeholder="仓库" clearable>
          <el-option v-for="item in warehouseList" :key="item.value" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createDateStart" format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="开始日期" clearable/>
        <el-date-picker v-model="searchForm.createDateEnd" format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="结束日期" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:check:save')" type="primary" @click="editHandle()">新增盘点</el-button>
<!--        <el-button v-if="isAuth('psi:check:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>-->
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="no" header-align="center" align="left" label="单号"/>
      <el-table-column prop="warehouseName" header-align="center" align="left" label="仓库"/>
      <el-table-column prop="createDate" header-align="center" align="left" label="盘点日期"/>
      <el-table-column prop="createName" header-align="center" align="left" label="操作人"/>
      <el-table-column prop="memo" header-align="center" align="left" label="备注"/>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:check:info')" type="text" size="small" @click="showDetails(scope.row)">详情</el-button>
<!--          <el-button v-if="isAuth('psi:check:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>-->
          <el-button v-if="isAuth('psi:check:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <check-edit v-if="editVisible" ref="checkEdit" @refreshDataList="getDataList"/>
    <check-detail v-if="detailVisible" ref="checkDetail"/>
  </div>
</template>

<script>
  import checkEdit from './check-edit'
  import Options from '../sys/options'
  import CheckDetail from './check-detail'

  export default {
    data () {
      return {
        searchForm: {
          no: '',
          warehouseId: '',
          createDateStart: '',
          createDateEnd: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListSelections: [],
        editVisible: false,
        selectVisible: false,
        detailVisible: false,
        warehouseList: []

      }
    },
    components: {
      checkEdit,
      Options,
      CheckDetail
    },
    activated () {
      this.$http({
        url: '/psi/warehouse/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.warehouseList = data.list
        } else {
          this.warehouseList = []
        }
      })
      this.getDataList()
    },
    methods: {
      formatBool: (row, column, cellValue, index) => Options.formatArray(Options.yesno, cellValue),
      formatSex: (row, column, cellValue, index) => Options.formatArray(Options.genders, cellValue),
      formatDate: (row, column, cellValue, index) => Options.formatDate(row, column, cellValue, index),
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/check/list',
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
      showDetails (row) {
        this.detailVisible = true
        this.$nextTick(() => {
          this.$refs.checkDetail.init(row.id, true)
        })
      },
      // 新增 / 修改
      editHandle (id) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.checkEdit.init(id)
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
            url: '/psi/check/delete',
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
