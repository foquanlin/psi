<template>
  <div class="mod-bankbill">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <select-bank :search-form="searchForm"/>
      </el-form-item>
      <el-form-item>
        <select-supplier :search-form="searchForm"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1;getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:bankbill:save')" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="isAuth('psi:bankbill:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="supplierId" header-align="center" align="center" label="客户供应商"/>
      <el-table-column prop="type" header-align="center" align="center" label="类型"/>
      <el-table-column prop="bankId" header-align="center" align="center" label="银行账户"/>
      <el-table-column prop="no" header-align="center" align="center" label="订单编号"/>
      <el-table-column prop="amount" header-align="center" align="center" label="金额"/>
      <el-table-column prop="createDate" header-align="center" align="center" label="日期"/>
      <el-table-column prop="createUid" header-align="center" align="center" label="制单人"/>
      <el-table-column prop="ownerUid" header-align="center" align="center" label="负责人"/>
      <el-table-column prop="memo" header-align="center" align="center" label="备注"/>
      <el-table-column prop="attachUrls" header-align="center" align="center" label="附件"/>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:bankbill:info')" type="text" size="small" @click="showDetails(scope.row.id)">查看</el-button>
          <el-button v-if="isAuth('psi:bankbill:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('psi:bankbill:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <bank-bill-edit v-if="editVisible" ref="bankbillEdit" @refreshDataList="getDataList"/>
  </div>
</template>

<script>
  import BankBillEdit from './bankbill-edit'
  import SelectSupplier from './component/select-supplier'
  import SelectBank from './component/select-bank'
  export default {
    data () {
      return {
        searchForm: {
          bankId: '',
          supplierId: '',
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
      BankBillEdit,
      SelectSupplier,
      SelectBank
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/bankbill/list',
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
          this.$refs.bankbillEdit.init(id, true)
        })
      },
      // 新增 / 修改
      editHandle (id) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.bankbillEdit.init(id)
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
            url: '/psi/bankbill/delete',
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
