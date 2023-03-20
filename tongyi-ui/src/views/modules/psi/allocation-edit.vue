<template>
  <el-drawer
    :title="!dataForm.id ? '新增调拨单' : !disabled ? '修改调拨单' : '查看调拨单'"
    :close-on-click-modal="false" size="90%" :visible.sync="visible">
    <el-form :model="dataForm"  :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="调出仓库" prop="outWarehouseId">
        <el-radio-group v-model="dataForm.outWarehouseId" placeholder="调出仓库" clearable :disabled="disabled">
          <el-radio-button v-for="item in warehouseList" :key="item.value" :label="item.id">{{item.name}}</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="调入仓库" prop="inWarehouseId">
        <el-radio-group v-model="dataForm.inWarehouseId" placeholder="调入仓库" clearable :disabled="disabled">
          <el-radio-button v-for="item in warehouseList" :key="item.value" :label="item.id">{{item.name}}</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="appendGoods" v-if="!disabled">增加商品</el-button>
      </el-form-item>
      <el-table border :data="dataList" style="margin-left: 10px;margin-right: 10px;">
        <el-table-column prop="name" header-align="center" align="center" label="商品">
          <template v-slot="scope">
            <span>{{scope.row.goodsName}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="skuId" header-align="center" align="center" label="规格">
          <template v-slot="scope">
            <span>{{scope.row.specName}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="catalog" header-align="center" align="center" label="分类">
          <template v-slot="scope">
            <span>{{scope.row.catalogName}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="num" header-align="center" align="center" label="数量" width="250px">
          <template v-slot="scope">
            <el-input-number v-model="scope.row.num" placeholder="数量"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column prop="unitId" header-align="center" align="center" label="单位">
          <template v-slot="scope">
            <span>{{scope.row.unitName}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="memo" header-align="center" align="center" label="备注">
          <template v-slot="scope">
            <el-input v-model="scope.row.memo" placeholder="备注"></el-input>
          </template>
        </el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
          <template v-slot="scope">
            <el-button type="text" size="small" @click="deleteHandle(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-form-item style="margin-top:20px;text-align: center">
        <el-button @click="visible = false">取消</el-button>
        <el-button v-if="!disabled" type="primary" @click="dataFormSubmit()">确定</el-button>
      </el-form-item>
    </el-form>
    <goods-select v-if="selectVisible" ref="goodsSelect" @select="onSelect"/>
  </el-drawer>
</template>

<script>
  import GoodsSelect from './goods-select'
  export default {
    data () {
      return {
        disabled: false,
        visible: false,
        selectVisible: false,
        dataForm: {
          id: '',
          no: '',
          outWarehouseId: '',
          inWarehouseId: '',
          createDate: '',
          memo: ''
        },
        dataList: [],
        warehouseList: [],
        dataRule: {
          no: [{required: true, message: '单号不能为空', trigger: 'blur'}],
          outWarehouseId: [{required: true, message: '出库仓库不能为空', trigger: 'blur'}],
          inWarehouseId: [{required: true, message: '入库仓库不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '创建日期不能为空', trigger: 'blur'}],
          other: []
        }
      }
    },
    components: {
      GoodsSelect
    },
    methods: {
      init (id, disabled) {
        this.disabled = disabled
        this.dataForm.id = id || ''
        this.visible = true
        this.dataList = []
        this.$nextTick(() => {
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
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: `/psi/allocation/info/${this.dataForm.id}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm = data.info
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm']
          .validate((valid) => {
            if (valid) {
              this.$http({
                url: `/psi/allocation/${!this.dataForm.id ? 'save' : 'update'}`,
                method: 'post',
                data: {
                  ...this.dataForm,
                  dataList: this.dataList
                }
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.$message({ message: '操作成功', type: 'success', duration: 1500 })
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            }
          })
      },
      appendGoods () {
        if (!this.dataForm.outWarehouseId) {
          this.$message({ message: '请选择调出仓库', type: 'info', duration: 3000 })
          return
        }
        this.selectVisible = true
        this.$nextTick(() => {
          this.$refs.goodsSelect.init(this.dataForm.outWarehouseId, this.dataList)
        })
      },
      onSelect (list) {
        let datalist = []
        list.forEach(item => {
          datalist.push({
            goodsId: item.goodsId,
            goodsName: item.goods.name,
            specName: item.specName,
            catalogName: item.goods.catalog.name,
            unitName: item.goods.unit.name,
            skuId: item.id,
            beforeNum: item.warehouseNum,
            afterNum: 0,
            memo: ''
          })
        })
        this.dataList = datalist
      },
      // 删除一行
      deleteHandle (index) {
        this.dataList.splice(index, 1)
      }
    }
  }
</script>
