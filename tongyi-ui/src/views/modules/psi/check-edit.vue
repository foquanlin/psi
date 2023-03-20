<template>
  <el-drawer :title="!dataForm.id ? '新增盘点' : !disabled ? '修改盘点' : '查看'" :close-on-click-modal="false" size="85%" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="80px" @keyup.enter.native="dataFormSubmit()">
      <el-form-item label="仓库" prop="warehouseId">
        <el-select v-model="dataForm.warehouseId" placeholder="仓库" clearable>
          <el-option v-for="item in warehouseList" :key="item.value" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable style="width: 400px;"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="appendGoods">增加商品</el-button>
      </el-form-item>
      <el-table border :data="dataList" style="width: 100%;">
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
        <el-table-column prop="beforeNum" header-align="center" align="center" label="库存数量"/>
        <el-table-column prop="afterNum" header-align="center" align="center" label="盘点数量" width="250px">
          <template v-slot="scope">
            <el-input-number v-model="scope.row.afterNum" placeholder="盘点数量"></el-input-number>
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
        dataForm: {
          id: '',
          no: '',
          warehouseId: '',
          createDate: '',
          memo: ''
        },
        dataRule: {
          warehouseId: [{required: true, message: '仓库不能为空', trigger: 'blur'}],
          other: []
        },
        selectVisible: false,
        warehouseList: [],
        dataList: []
      }
    },
    components: {
      GoodsSelect
    },
    mounted () {
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
    },
    methods: {
      init (id, disabled) {
        this.disabled = disabled
        this.dataForm.id = id || ''
        this.dataList = []
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: `/psi/check/info/${this.dataForm.id}`,
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
                url: `/psi/check/${!this.dataForm.id ? 'save' : 'update'}`,
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
      // 增加盘点商品
      appendGoods () {
        if (!this.dataForm.warehouseId) {
          this.$message({ message: '请选择需要盘点仓库', type: 'info', duration: 3000 })
          return
        }
        this.selectVisible = true
        this.$nextTick(() => {
          this.$refs.goodsSelect.init(this.dataForm.warehouseId, this.dataList)
        })
      },
      // 删除一行
      deleteHandle (index) {
        this.dataList.splice(index, 1)
      },
      onSelect (list) {
        let datalist = []
        list.forEach(item => {
          datalist.push({
            goodsId: item.goodsId,
            goodsName: item.goodsName,
            specName: item.specName,
            catalogName: item.catalogName,
            unitName: item.unitName,
            skuId: item.id,
            beforeNum: item.warehouseNum,
            afterNum: 0,
            memo: ''
          })
        })
        this.dataList = datalist
      }
    }
  }
</script>
<style scoped>
</style>
