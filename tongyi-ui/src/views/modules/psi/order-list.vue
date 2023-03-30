<template>
  <div class="mod-order">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-select v-model="searchForm.status" :placeholder="descriptions.orderStatus" clearable >
          <el-option value="UNFINISH" label="未完成"></el-option>
          <el-option value="FINISH" label="已完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.payStatus" :placeholder="descriptions.payStatus" clearable >
          <el-option value="DEBT" label="有欠款"></el-option>
          <el-option value="PAYMENT" label="待收款"></el-option>
          <el-option value="FINISH" label="已完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.stockStatus" :placeholder="descriptions.stockStatus" clearable >
          <el-option value="UNFINISH" label="未完成"></el-option>
          <el-option value="FINISH" label="已完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.invoiceStatus" :placeholder="descriptions.invoiceStatus" clearable >
          <el-option value="UNFINISH" label="未开发票"></el-option>
          <el-option value="FINISH" label="已开发票"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.no" :placeholder="descriptions.no" clearable/>
      </el-form-item>
      <el-form-item>
        <select-supplier2 v-model="searchForm" field="orderUid" :type="catalog==='BUY'?'SUPPLIER':'CUSTOMER'"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createDateStart" :placeholder="descriptions.createDateStart" clearable type="date" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchForm.createDateEnd" :placeholder="descriptions.createDateEnd" clearable type="date" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <select-user v-model="searchForm" field="createUid" :placeholder="descriptions.createUid"/>
      </el-form-item>
      <el-form-item>
        <select-user v-model="searchForm" field="ownerUid" :placeholder="descriptions.ownerUid"/>
      </el-form-item>
      <el-form-item>
        <select-goods v-model="searchForm" field="goodsId"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.memo" :placeholder="descriptions.memo" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
        <el-button v-if="isAuth('psi:buyorder:save')" type="primary" @click="addHandle()">新增</el-button>
        <el-button v-if="isAuth('psi:buyorder:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" @selection-change="selectionChangeHandle" style="width: 100%;margin-bottom: 10px">
      <el-table-column type="selection" header-align="center" align="center" width="50"/>
      <el-table-column prop="no" header-align="center" align="center" :label="descriptions.no">
        <template v-slot="scope">
          <el-button type="text" @click="showDetails(scope.row.id)">{{scope.row.no}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="supplierName" header-align="center" align="center" :label="supplierName">
        <template v-slot="scope">
          <el-button type="text" @click="showSupplier(scope.row.orderUser.id)">{{scope.row.orderUser?scope.row.orderUser.name:'-'}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="createDate" header-align="center" align="center" :label="descriptions.createDate"/>
      <el-table-column prop="createUid" header-align="center" align="center" :label="descriptions.createUid">
        <template v-slot="scope">
          <span>{{scope.row.createUser?scope.row.createUser.realName:'-'}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="ownerUid" header-align="center" align="center" :label="descriptions.ownerUid">
        <template v-slot="scope">
          <span>{{scope.row.ownerUser?scope.row.ownerUser.realName:'-'}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="payAmount" header-align="center" align="right" :label="descriptions.payAmount"/>
      <el-table-column prop="orderAmount" header-align="center" align="right" :label="descriptions.orderAmount"/>
      <el-table-column prop="payStatus" header-align="center" align="center" :label="descriptions.payStatus">
        <template v-slot="scope">
          <el-tag v-if="scope.row.payStatus === 'DEBT'">有欠款</el-tag>
          <el-tag v-else-if="scope.row.payStatus === 'PAYMENT'">待收款</el-tag>
          <el-tag v-else-if="scope.row.payStatus === 'FINISH'">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="stockStatus" header-align="center" align="center" :label="descriptions.stockStatus">
        <template v-slot="scope">
          <el-tag v-if="scope.row.stockStatus === 'UNFINISH'">未完成</el-tag>
          <el-tag v-else-if="scope.row.stockStatus === 'FINISH'">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" header-align="center" align="center" :label="descriptions.orderStatus">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status === 'UNFINISH'">未完成</el-tag>
          <el-tag v-else-if="scope.row.status === 'FINISH'">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="invoiceStatus" header-align="center" align="center" :label="descriptions.invoiceStatus">
        <template v-slot="scope">
          <el-tag v-if="scope.row.invoiceStatus === 'UNFINISH'">未开发票</el-tag>
          <el-tag v-else-if="scope.row.invoiceStatus === 'FINISH'">已开发票</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" :label="descriptions.action">
        <template v-slot="scope">
          <el-button v-if="isAuth('psi:buyorder:info')" type="text" size="small" @click="showDetails(scope.row.id)">{{ descriptions.view }}</el-button>
          <el-button v-if="isAuth('psi:buyorder:update')" type="text" size="small" @click="editHandle(scope.row.id)">
            {{ descriptions.edit }}</el-button>
          <el-button v-if="isAuth('psi:buyorder:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">
            {{ descriptions.delete }}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row :gutter="12">
      <el-col :span="4">
        <el-statistic :title="descriptions.total">
          <template slot="formatter">{{totalAmount}}</template>
        </el-statistic>
      </el-col>
      <el-col :span="4">
        <el-statistic :title="descriptions.payAmount">
          <template slot="formatter">{{payAmount}}</template>
        </el-statistic>
      </el-col>
      <el-col :span="4">
        <el-statistic :title="descriptions.nopayAmount">
          <template slot="formatter">{{nopayAmount}}</template>
        </el-statistic>
      </el-col>
    </el-row>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
                   :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <brand-edit v-if="editVisible" ref="brandEdit"/>
    <user-edit v-if="userVisible" ref="userEdit"/>
    <order-add v-if="orderAddVisible" ref="orderAdd" :descriptions="descriptions" :catalog="catalog" :type="type" @refreshDataList="getDataList"/>
    <order-edit v-if="orderEditVisible" ref="orderEdit" :descriptions="descriptions" :catalog="catalog" :type="type" @refreshDataList="getDataList"/>
    <order-view v-if="orderViewVisible" ref="orderView" :descriptions="descriptions" :catalog="catalog" :type="type"/>

  </div>
</template>

<script>
import UserEdit from './user-edit'
import OrderAdd from './order-add'
import OrderEdit from './order-edit'
import OrderView from './order-view'
import SelectGoods from './component/select-goods'
import SelectUser from './component/select-user'
import SelectSupplier2 from './component/select-supplier2'
import Options from './options'
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
      userVisible: false,
      orderAddVisible: false,
      orderEditVisible: false,
      orderViewVisible: false,
      totalAmount: 0,
      payAmount: 0,
      nopayAmount: 0,
      catalogName: '',
      payName: '',
      supplierName: ''
    }
  },
  props: {
    catalog: {
      type: String,
      default: undefined
    },
    type: {
      type: String,
      default: undefined
    },
    descriptions: {
      type: Object,
      default: {}
    }
  },
  watch: {
    catalog: {
      immediate: true,
      handler (value) {
        this.catalog = value
        this.payName = Options.payName(this.catalog, this.type)
        this.catalogName = Options.catalogName(this.catalog, this.type)
        this.supplierName = Options.supperName(this.catalog, this.type)
        console.log('watch.catalog')
      }
    },
    type: {
      immediate: true,
      handler (value) {
        this.type = value
        console.log('watch.type')
      }
    }
  },
  components: {
    UserEdit,
    OrderAdd,
    OrderEdit,
    OrderView,
    SelectGoods,
    SelectUser,
    SelectSupplier2,
    Options
  },
  mounted () {
    this.getDataList()
  },
  methods: {
    getDataList () {
      this.$http({
        url: '/psi/order/list',
        method: 'get',
        params: {
          page: this.pageIndex,
          limit: this.pageSize,
          catalog: this.catalog,
          type: this.type,
          ...this.searchForm
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list
          this.totalPage = data.page.total
          let total = 0.00
          let pay = 0.00
          let nopay = 0.00
          this.dataList.forEach(item => {
            total += item.orderAmount
            pay += item.payAmount
            nopay += item.orderAmount - item.payAmount
          })
          this.totalAmount = total
          this.payAmount = pay
          this.nopayAmount = nopay
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
      this.orderViewVisible = true
      this.$nextTick(() => {
        this.$refs.orderView.init(id)
      })
    },
    // 新增
    addHandle (id) {
      this.orderAddVisible = true
      this.$nextTick(() => {
        this.$refs.orderAdd.init(id, false)
      })
    },
    // 修改
    editHandle (id) {
      this.orderEditVisible = true
      this.$nextTick(() => {
        this.$refs.orderEdit.init(id, false)
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
    },
    showSupplier (id) {
      this.userVisible = true
      this.$nextTick(() => {
        this.$refs.userEdit.init(id, true)
      })
    }
  }
}
</script>
