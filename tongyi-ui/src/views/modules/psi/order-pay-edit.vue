<!--订单付款列表-->
<template>
  <div>
    <el-table border :data="dataList">
      <el-table-column prop="createDate" header-align="center" align="center" :label="descriptions.payDate">
        <template v-slot="scope">
          <el-date-picker v-if="scope.row.edited" v-model="scope.row.createDate" :placeholder="descriptions.payDate" clearable type="date" value-format="yyyy-MM-dd" size="mini"/>
          <span v-else>{{scope.row.createDate}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="bankId" header-align="center" align="center" :label="descriptions.payAccount">
        <template v-slot="scope">
          <select-bank v-if="scope.row.edited" v-model="scope.row" field="bankId" :placeholder="descriptions.payAccount" size="mini"/>
          <span v-else>{{scope.row.bankName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="amount" header-align="center" align="center" :label="descriptions.amount">
        <template v-slot="scope">
          <el-input-number v-if="scope.row.edited" v-model="scope.row.amount" :placeholder="descriptions.amount" size="mini"/>
          <span v-else>{{scope.row.amount}}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" :label="descriptions.action" width="150">
        <template v-slot="scope">
          <el-button type="text" size="small" @click="savePay(scope.row, scope.$index)" v-if="scope.row.edited">{{ descriptions.save }}</el-button>
          <el-button type="text" size="small" @click="cancelPay(scope.row, scope.$index)" v-if="scope.row.id && scope.row.edited">
            {{descriptions.cancel}}</el-button>
          <el-button type="text" size="small" @click="editPay(scope.row, scope.$index)" v-if="scope.row.id && !scope.row.edited &&
          (isAuth('psi:buyorder:updateAmount') || isAuth('psi:buyrefundorder:updateAmount') || isAuth('psi:saleorder:updateAmount') || isAuth('psi:salerefundorder:updateAmount'))">
            {{ descriptions.edit }}</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row, scope.$index)"
            v-if="isAuth('psi:buyorder:deleteAmount') || isAuth('psi:buyrefundorder:deleteAmount') || isAuth('psi:saleorder:deleteAmount') || isAuth('psi:salerefundorder:deleteAmount')">
            {{ descriptions.delete }}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="text" @click="addRow()" size="mini" icon="el-icon-plus"
      v-if="isAuth('psi:buyorder:addAmount') || isAuth('psi:buyrefundorder:addAmount') || isAuth('psi:saleorder:addAmount') || isAuth('psi:salerefundorder:addAmount')">
      {{ descriptions.add }}{{payName}}</el-button>
  </div>
</template>

<script>
import SelectBank from './component/select-bank'
import Options from './options'
export default {
  data () {
    return {
      edited: function () {
        let ed = false
        this.dataList.forEach(item => {
          if (item.edited) {
            ed = true
          }
        })
        return ed
      },
      amountEditVisible: false,
      bankList: [],
      payType: '',
      payName: ''
    }
  },
  props: {
    order: {
      type: Object
    },
    dataList: {
      type: Array
    },
    catalog: {
      type: String,
      require: true
    },
    type: {
      type: String,
      require: true
    },
    descriptions: {
      type: Object,
      default: {}
    }
  },
  watch: {
    order: {
      immediate: true,
      handler (value) {
        this.order = value
      }
    },
    dataList: {
      immediate: true,
      handler (value) {
        this.dataList = value
        console.log('watch.dataList')
      },
      deep: true
    },
    catalog: {
      immediate: true,
      handler (value) {
        this.payName = Options.payName(this.catalog, this.type)
        this.payType = Options.payType(this.catalog, this.type)
      }
    },
    type: {
      immediate: true,
      handler (value) {
        this.type = value
      }
    }
  },
  components: {
    SelectBank,
    Options
  },
  methods: {
    deleteHandle (row, index) {
      if (!row.id) {
        this.dataList.splice(index, 1)
        return
      }
      this.$confirm(`确定进行[删除]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: `/psi/orderamount/delete/${row.id}`,
          method: 'get'
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({ message: '操作成功', type: 'success', duration: 1500 })
            this.$emit('refreshDataList')
          }
        })
      })
    },
    addRow () {
      this.dataList.push({edited: true, createDate: '', amount: 0, type: this.payType})
    },
    savePay (row, index) {
      this.$http({
        url: `/psi/orderamount/${!row.id ? 'save' : 'update'}`,
        method: 'post',
        data: {
          orderId: this.order.id,
          ...row
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message({ message: '操作成功', type: 'success', duration: 1500 })
          this.$emit('refreshDataList')
        }
      })
    },
    editPay (row, index) {
      row.edited = true
    },
    cancelPay (row, index) {
      row.edited = false
    }
  }
}
</script>
