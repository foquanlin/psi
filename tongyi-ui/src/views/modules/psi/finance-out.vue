<template>
  <div class="mod-finance">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="searchForm.no" placeholder="订单编号" clearable/>
      </el-form-item>
      <el-form-item>
        <select-finance-type v-model="searchForm" field="typeId" type="IN" placeholder="类型"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createDate" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <select-supplier v-model="searchForm" field="supplierId" type="CUSTOMER"/>
      </el-form-item>
      <el-form-item>
        <select-user v-model="searchForm" field="createUid" placeholder="制单人"/>
      </el-form-item>
      <el-form-item>
        <select-user v-model="searchForm" field="ownerUid" placeholder="负责人"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.memo" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1;getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:nosalein:save')" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="isAuth('psi:nosalein:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="no" header-align="center" align="center" label="编号"/>
      <el-table-column prop="typeName" header-align="center" align="center" label="类型"/>
      <el-table-column prop="createDate" header-align="center" align="center" label="日期"/>
      <el-table-column prop="supplierName" header-align="center" align="center" label="付款方"/>
      <el-table-column prop="createName" header-align="center" align="center" label="创建人"/>
      <el-table-column prop="ownerName" header-align="center" align="center" label="负责人"/>
      <el-table-column prop="memo" header-align="center" align="center" label="备注"/>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:nosalein:info')" type="text" size="small" @click="showDetails(scope.row.id)">查看</el-button>
          <el-button v-if="isAuth('psi:nosalein:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('psi:nosalein:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
                   :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <finance-edit v-if="editVisible" type="OUT" ref="financeEdit" @refreshDataList="getDataList"/>
    <finance-view v-if="viewVisible" ref="financeView"/>
  </div>
</template>

<script>
import SelectUser from './component/select-user'
import SelectSupplier from './component/select-supplier'
import SelectFinanceType from './component/select-finance-type'
import FinanceEdit from './finance-edit'
import FinanceView from './finance-view'
export default {
  data () {
    return {
      searchForm: {
        no: '',
        typeId: '',
        supplierId: '',
        createDate: [],
        createUid: '',
        ownerUid: '',
        memo: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListSelections: [],
      editVisible: false,
      viewVisible: false
    }
  },
  components: {
    SelectUser,
    SelectSupplier,
    SelectFinanceType,
    FinanceEdit,
    FinanceView
  },
  activated () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.$http({
        url: '/psi/finance/list',
        method: 'get',
        params: {
          page: this.pageIndex,
          limit: this.pageSize,
          catalog: 'OUT',
          createDateStart: this.searchForm.createDate ? this.searchForm.createDate[0] : null,
          createDateEnd: this.searchForm.createDate ? this.searchForm.createDate[1] : null,
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
      this.viewVisible = true
      this.$nextTick(() => {
        this.$refs.financeView.init(id, true)
      })
    },
    // 新增 / 修改
    editHandle (id) {
      this.editVisible = true
      this.$nextTick(() => {
        this.$refs.financeEdit.init(id)
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
          url: '/psi/order/delete',
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
