<template>
  <el-drawer title="选择商品" :close-on-click-modal="false" size="60%" :append-to-body="true" :visible.sync="visible">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()" style="margin-left: 10px;margin-right: 10px;">
      <el-form-item>
        <el-input v-model="searchForm.no" placeholder="编码" clearable suffix-icon="el-icon-search"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.name" placeholder="名称" clearable suffix-icon="el-icon-search"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.barcode" placeholder="条形码" clearable suffix-icon="el-icon-search"/>
      </el-form-item>
      <el-form-item>
        <select-catalog v-model="searchForm" field="catalogId"/>
      </el-form-item>
      <el-form-item>
        <select-warehouse v-model="searchForm" field="warehouseId" v-if="selectWarehouseId"></select-warehouse>
      </el-form-item>
      <el-form-item>
        <el-button @click="pageIndex = 1; getDataList()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" ref="fileTable" style="margin-left:10px;margin-right:10px;width: 95%;" @selection-change="selectionChangeHandle" row-key="id">
      <el-table-column type="selection" header-align="center" align="center" width="50" :reserve-selection="true"/>
      <el-table-column prop="name" header-align="center" align="center" label="名称">
        <template v-slot="scope">
          {{scope.row.goodsName}}
        </template>
      </el-table-column>
      <el-table-column prop="name" header-align="center" align="left" label="规格" width="280">
        <template v-slot="scope">
          <el-tag type="info" v-if="scope.row.specName" v-for="item in scope.row.specName.split(':')" :key="item" style="margin-right: 10px;">{{item}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="catalogId" header-align="center" align="left" label="分类">
        <template v-slot="scope">
          <span>{{scope.row.catalogName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="warehouseNum" header-align="center" align="right" label="库存"/>
      <el-table-column prop="unitId" header-align="center" align="left" label="单位">
        <template v-slot="scope">
          <span>{{scope.row.unitName}}</span>
        </template>
      </el-table-column>
    </el-table>
    <el-row style="margin-left:10px;margin-right:10px;width: 80%;">
      <span style="text-align: right;">
      <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
                     :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
      </span>
    </el-row>
    <el-row style="margin-left:10px;margin-right:10px;">
      <span style="text-align: right;">
        <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
        <el-button @click="visible = false">取消</el-button>
      </span>
    </el-row>
  </el-drawer>
</template>

<script>
  import SelectWarehouse from './component/select-warehouse'
  import SelectCatalog from './component/select-catalog'

  export default {
    data () {
      return {
        searchForm: {
          name: '',
          warehouseId: '',
          catalogId: '',
          no: '',
          barcode: '',
          status: ''
        },
        selectWarehouseId: true,
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListSelections: [],
        visible: false
      }
    },
    components: {
      SelectWarehouse,
      SelectCatalog
    },
    watch: {
      dataList: function () {
        this.$nextTick(() => {
          console.log('-----dataList----')
          this.dataListSelections.forEach(item => {
            this.dataList.forEach(item2 => {
              if (item.id === item2.id) {
                this.$refs.fileTable.toggleRowSelection(item2, true)
              }
            })
          })
        })
      }
    },
    methods: {
      init (warehouseId, list) {
        console.log('warehouseId=', warehouseId)
        this.selectWarehouseId = !warehouseId
        console.log('this.selectWarehouseId=', this.selectWarehouseId)
        this.visible = true
        if (warehouseId) {
          this.dataListSelections = list || []
          this.searchForm.warehouseId = warehouseId
        }
        this.getDataList()
      },
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/stock/skulist',
          method: 'post',
          data: {
            page: this.pageIndex,
            limit: this.pageSize,
            ...this.searchForm
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.dataList.forEach(item => {
              item.memo = ''
            })
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
        // :reserve-selection="true" 表格数据改变是保留选择的数据
        this.dataListSelections = val
      },
      dataFormSubmit () {
        this.visible = false
        this.$emit('select', this.dataListSelections)
      }
    }
  }
</script>
