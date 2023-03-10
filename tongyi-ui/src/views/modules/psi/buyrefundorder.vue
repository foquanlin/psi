<template>
  <div class="mod-buyorder">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-select v-model="searchForm.status" placeholder="订单状态" clearable >
          <el-option value="UNFINISH" label="未完成"></el-option>
          <el-option value="FINISH" label="已完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.payStatus" placeholder="收款状态" clearable >
          <el-option value="DEBT" label="有欠款"></el-option>
          <el-option value="PAYMENT" label="待收款"></el-option>
          <el-option value="FINISH" label="已完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.stockStatus" placeholder="入库状态" clearable >
          <el-option value="UNFINISH" label="未完成"></el-option>
          <el-option value="FINISH" label="已完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.invoiceStatus" placeholder="发票状态" clearable >
          <el-option value="UNFINISH" label="未开发票"></el-option>
          <el-option value="FINISH" label="已开发票"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.no" placeholder="订单号" clearable/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.orderUid" placeholder="客户" clearable>
          <el-option v-for="item in supplierList" :key="item.id" :value="item.id" :label="item.name"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createDateStart" placeholder="开始日期" clearable type="date" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createDateEnd" placeholder="结束日期" clearable type="date" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.createUid" placeholder="制单人" clearable>
          <el-option v-for="item in userList" :key="item.userId" :value="item.userId" :label="item.realName"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.ownerUid" placeholder="负责人" clearable>
          <el-option v-for="item in userList" :key="item.userId" :value="item.userId" :label="item.realName"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.memo" placeholder="订单备注" clearable/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.goodsId" placeholder="商品名称" clearable>
          <el-option v-for="goods in goodsList" :key="goods.id" :value="goods.id" :label="goods.name"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:buyorder:save')" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="isAuth('psi:buyorder:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="no" header-align="center" align="center" label="订单编号">
        <template v-slot="scope">
          <el-button type="text" @click="showDetails(scope.row.id)">{{scope.row.no}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="supplierName" header-align="center" align="center" label="供应商">
        <template v-slot="scope">
          <el-button type="text" @click="showSupplier(scope.row.orderUser.id)">{{scope.row.orderUser?scope.row.orderUser.name:'-'}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="createDate" header-align="center" align="center" label="采购时间"/>
      <el-table-column prop="createUid" header-align="center" align="center" label="制单人">
        <template v-slot="scope">
          <span>{{scope.row.createUser?scope.row.createUser.realName:'-'}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="ownerUid" header-align="center" align="center" label="负责人">
        <template v-slot="scope">
          <span>{{scope.row.ownerUser?scope.row.ownerUser.realName:'-'}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="payAmount" header-align="center" align="right" label="已付款"/>
      <el-table-column prop="orderAmount" header-align="center" align="right" label="订单总价"/>
      <el-table-column prop="payStatus" header-align="center" align="center" label="付款状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.payStatus === 'DEBT'">有欠款</el-tag>
          <el-tag v-else-if="scope.row.payStatus === 'PAYMENT'">待收款</el-tag>
          <el-tag v-else-if="scope.row.payStatus === 'FINISH'">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="stockStatus" header-align="center" align="center" label="入库状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.stockStatus === 'UNFINISH'">未完成</el-tag>
          <el-tag v-else-if="scope.row.stockStatus === 'FINISH'">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" header-align="center" align="center" label="订单状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status === 'UNFINISH'">未完成</el-tag>
          <el-tag v-else-if="scope.row.status === 'FINISH'">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="invoiceStatus" header-align="center" align="center" label="发票状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.invoiceStatus === 'UNFINISH'">未开发票</el-tag>
          <el-tag v-else-if="scope.row.invoiceStatus === 'FINISH'">已开发票</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:buyorder:info')" type="text" size="small" @click="showDetails(scope.row.id)">查看</el-button>
          <el-button v-if="isAuth('psi:buyorder:update')" type="text" size="small" @click="editHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('psi:buyorder:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
                   :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <brand-edit v-if="editVisible" ref="brandEdit"/>
    <supplier-edit v-if="supplierVisible" ref="supplierEdit"/>
    <buy-refund-order-edit v-if="buyRefundOrderVisible" ref="buyRefundOrderEdit" @refreshDataList="getDataList"/>

  </div>
</template>

<script>
import SupplierEdit from './supplier-edit'
import BuyRefundOrderEdit from './buyrefundorder-edit'
export default {
  data () {
    return {
      searchForm: {
        no: '',
        invoiceStatus: '',
        stockStatus: '',
        payStatus: '',
        status: '',
        ownerUid: '',
        createUid: '',
        orderUid: '',
        createDateStart: '',
        createDateEnd: '',
        memo: '',
        goodsId: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListSelections: [],
      editVisible: false,
      supplierVisible: false,
      buyRefundOrderVisible: false,
      supplierList: [],
      userList: [],
      goodsList: []
    }
  },
  components: {
    SupplierEdit,
    BuyRefundOrderEdit
  },
  activated () {
    this.loadSupplier()
    this.loadUser()
    this.loadGoods()
    this.getDataList()
  },
  methods: {
    getDataList () {
      this.$http({
        url: '/psi/buyorder/list',
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
      this.buyRefundOrderVisible = true
      this.$nextTick(() => {
        this.$refs.buyRefundOrderEdit.init(id, true)
      })
    },
    // 新增 / 修改
    editHandle (id) {
      this.buyRefundOrderVisible = true
      this.$nextTick(() => {
        this.$refs.buyRefundOrderEdit.init(id, false)
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
          url: '/psi/buyorder/delete',
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
    loadSupplier () {
      this.$http({
        url: '/psi/supplier/listAll',
        method: 'get',
        params: {
          type: 'CUSTOMER'
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.supplierList = data.list
        } else {
          this.supplierList = []
        }
      })
    },
    loadGoods () {
      this.$http({
        url: '/psi/goods/listAll',
        method: 'get',
        data: {
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.goodsList = data.list
        } else {
          this.goodsList = []
        }
      })
    },
    loadUser () {
      this.$http({
        url: '/sys/user/queryAll',
        method: 'get',
        data: {
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.userList = data.list
        } else {
          this.userList = []
        }
      })
    },
    showSupplier (id) {
      this.supplierVisible = true
      this.$nextTick(() => {
        this.$refs.supplierEdit.init(id, true)
      })
    }
  }
}
</script>
