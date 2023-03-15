<!--订单付款列表-->
<template>
  <div>
  <el-table border :data="accountList" style="align-content: center;align-items: center;">
    <el-table-column prop="createDate" header-align="center" align="center" label="日期"/>
    <el-table-column prop="bankId" header-align="center" align="center" label="账户">
      <template v-slot="scope">
        {{scope.row.bank.bankName}}
      </template>
    </el-table-column>
    <el-table-column prop="amount" header-align="center" align="center" label="金额"/>
    <el-table-column fixed="right" header-align="center" align="center" width="150">
      <template v-slot="scope">
        <el-button type="text" size="small" @click="editPay(scope.row.id)">修改</el-button>
        <el-button type="text" size="small" @click="deleteHandle(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-button type="text" @click="editPay('')" size="mini" icon="el-icon-plus">增加付款</el-button>
    <order-amount-edit v-if="amountEditVisible" ref="orderAmountEdit" @refreshDataList="getDataList"/>
  </div>
</template>

<script>
import OrderAmountEdit from './order-amount-edit'

export default {
  data () {
    return {
      accountList: [],
      edited: false,
      amountEditVisible: false
    }
  },
  props: {
    orderId: {
      type: String,
      default: true
    }
  },
  watch: {
    orderId: {
      immediate: true,
      handler (value) {
        this.orderId = value
        this.getDataList()
      }
    }
  },
  components: {
    OrderAmountEdit
  },
  mounted () {
    this.getDataList()
  },
  methods: {
    getDataList () {
      this.$http({
        url: `/psi/orderamount/listAll`,
        method: 'post',
        params: {
          orderId: this.orderId
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.accountList = data.list
        }
      })
    },
    deleteHandle (row) {
      this.$confirm(`确定进行[删除]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/psi/orderamount/delete',
          method: 'post',
          data: [row.id]
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({ message: '操作成功', type: 'success', duration: 1500 })
            this.getDataList()
          }
        })
      })
    },
    editPay (id) {
      this.amountEditVisible = true
      this.$nextTick(() => {
        this.$refs.orderAmountEdit.init(this.orderId, id)
      })
    }
  }
}
</script>
