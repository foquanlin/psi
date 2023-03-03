<template>
  <el-drawer :title="!dataForm.id ? '新增' : !disabled ? '修改' : '查看'" :close-on-click-modal="false" size="90%" :visible.sync="visible">
    <el-row :gutter="24">
      <el-col :span="18"><div class="grid-content bg-purple-light">&nbsp;</div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-button @click="visible = false">取消</el-button>
        <el-button v-if="!disabled" type="primary" @click="dataFormSubmit()">确定</el-button>
      </div>
      </el-col>
    </el-row>
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" label-width="120px" style="margin-top: 20px">
      <el-tabs tab-position="top" type="border-card" >
        <el-tab-pane label="商品信息">
          <el-form-item label="品牌" prop="brandId">
            <el-select v-model="dataForm.brandId" :disabled="disabled" placeholder="品牌" clearable>
              <el-option v-for="item in brandList" :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item label="分类" prop="catalogId">
            <el-select v-model="dataForm.catalogId" :disabled="disabled" placeholder="分类" clearable>
              <el-option v-for="item in catalogList" :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item label="商品编码" prop="no">
            <el-input v-model="dataForm.no" :disabled="disabled" placeholder="商品编码" clearable/>
          </el-form-item>
          <el-form-item label="名称" prop="name">
            <el-input v-model="dataForm.name" :disabled="disabled" placeholder="名称" clearable/>
          </el-form-item>
          <el-form-item label="单位" prop="unitId">
            <el-select v-model="dataForm.unitId" :disabled="disabled" placeholder="单位" clearable>
              <el-option v-for="item in unitList" :key="item.id" :label="item.name" :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item label="备注" prop="memo">
            <el-input v-model="dataForm.memo" :disabled="disabled" placeholder="备注" clearable/>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-input v-model="dataForm.status" :disabled="disabled" placeholder="状态" clearable/>
          </el-form-item>
        </el-tab-pane>

        <el-tab-pane label="SKU">
          <table style="width:100%">
            <tr>
              <th style="background-color: #bfcbd9">规格名称</th>
              <th style="background-color: #bfcbd9">规格值</th>
              <th style="background-color: #bfcbd9">操作</th>
              <th></th>
            </tr>
            <tr v-for="(spec,idx) in dataForm.specList">
              <th><el-select v-model="spec.specName" :disabled="disabled" placeholder="选择规格或输入自定义规格按回车" style="width:100%" clearable allow-create filterable default-first-option/></th>
              <th><el-select v-model="spec.specValue" :disabled="disabled" placeholder="输入完成按回车或点击保存可新增多个规格值" @change="changeSpecValue" @remove-tag="changeSpecValue" style="width:100%" multiple allow-create filterable default-first-option/></th>
              <th>
<!--                <el-button @click="saveSpec(idx)">保存</el-button>-->
                <el-button @click="delSpec(idx)">删除</el-button>
              </th>
            </tr>
            <tr>
              <td><el-button icon="el-icon-plus" @click="addSpec" >添加规格</el-button></td>
            </tr>
          </table>
          <table>
            <tr>
              <th style="background-color: #bfcbd9">规格值</th>
              <th style="background-color: #bfcbd9">条形码</th>
              <th style="background-color: #bfcbd9">参考进价</th>
              <th style="background-color: #bfcbd9">参考售价</th>
              <th style="background-color: #bfcbd9">库存数量</th>
              <th style="background-color: #bfcbd9">仓库分配</th>
              <th style="background-color: #bfcbd9">操作</th>
            </tr>
            <tr v-for="(item,idx) in dataForm.skuList">
              <td><el-input v-model="item.specName" placeholder="规格"></el-input></td>
              <td><el-input v-model="item.barcode" placeholder="条形码"></el-input></td>
              <td><el-input v-model="item.costPrice" placeholder="进价"></el-input></td>
              <td><el-input v-model="item.salePrice" placeholder="售价"></el-input></td>
              <td><el-input v-model="item.num" :disabled="true"></el-input></td>
              <td><el-input v-model="item.warehouse"></el-input></td>
              <td>
                <el-button @click="inStock">入库</el-button>
                <el-button @click="delSku(idx)" >删除</el-button>
              </td>
            </tr>
          </table>
<!--          <el-row v-for="(item,idx) in specForm.specList">-->
<!--            <el-form-item label="规格名称" prop="specName">-->
<!--              <el-input v-model="dataForm.status" :disabled="disabled" placeholder="状态" clearable/>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="规格值" prop="specValue">-->
<!--              <el-input v-model="dataForm.status" :disabled="disabled" placeholder="状态" clearable/>-->
<!--            </el-form-item>-->
<!--          </el-row>-->
          <el-divider></el-divider>
<!--          <el-row v-for="item in dataForm.skuList">-->
<!--            <el-form-item label="状态" prop="status">-->
<!--              <el-input v-model="dataForm.status" :disabled="disabled" placeholder="状态" clearable/>-->
<!--            </el-form-item>-->
<!--          </el-row>-->
        </el-tab-pane>
        <el-tab-pane label="图片">
          <el-form-item label="图片" prop="picUrls">
            <el-img v-model="dataForm.picUrls" :disabled="disabled" placeholder="图片" clearable/>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
    </el-form>
  </el-drawer>
</template>

<script>
  export default {
    data () {
      return {
        disabled: false,
        visible: false,
        dataForm: {
          id: '',
          brandId: '',
          catalogId: '',
          no: '',
          name: '',
          createDate: '',
          unitId: '',
          picUrls: '',
          memo: '',
          status: '',
          specList: [{
            id: '',
            specName: '',
            specValue: []
          }],
          skuList: []
        },
        dataRule: {
          brandId: [{required: true, message: '品牌不能为空', trigger: 'blur'}],
          catalogId: [{required: true, message: '分类不能为空', trigger: 'blur'}],
          name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
          createDate: [{required: true, message: '创建时间不能为空', trigger: 'blur'}],
          unitId: [{required: true, message: '单位不能为空', trigger: 'blur'}],
          other: []
        },
        warehouseList: [],
        catalogList: [],
        brandList: [],
        unitList: []
      }
    },
    created () {
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
      this.$http({
        url: '/psi/catalog/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.catalogList = data.list
        } else {
          this.catalogList = []
        }
      })
      this.$http({
        url: '/psi/brand/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.brandList = data.list
        } else {
          this.brandList = []
        }
      })
      this.$http({
        url: '/psi/unit/listAll',
        method: 'get',
        params: {}
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.unitList = data.list
        } else {
          this.unitList = []
        }
      })
    },
    methods: {
      initData () {
        this.dataForm.skuList.push({
          id: '',
          specName: '无规格',
          barcode: '',
          costPrice: 0,
          salePrice: 0,
          num: 0,
          status: ''
        })
      },
      init (id, disabled) {
        this.disabled = disabled
        this.dataForm.id = id || ''
        this.dataForm.specList = []
        this.dataForm.skuList = []
        this.visible = true
        this.$nextTick(() => {
          this.initData()
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: `/psi/goods/info/${this.dataForm.id}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm = data.info
                if (this.dataForm.specList) {
                  this.dataForm.specList.forEach(item => {
                    item.specValue = item.specValue.split(',')
                  })
                }
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
              this.dataForm.specList.forEach(item => {
                item.specValue = item.specValue.join(',')
              })
              this.$http({
                url: `/psi/goods/${!this.dataForm.id ? 'save' : 'update'}`,
                method: 'post',
                data: this.dataForm
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
      addSpec () {
        this.dataForm.specList.push({
          id: '',
          specName: '',
          specValue: ''
        })
      },
      delSpec (index) {
        if (this.dataForm.specList.length === 1) {
          return
        }
        this.dataForm.specList.splice(index, 1)
        this.changeSpecValue([])
      },
      saveSpec (index) {
      },
      changeSpecValue (val) {
        this.dataForm.skuList = this.oldSkuList
        if (this.dataForm.specList.length === 0) {
          this.dataForm.skuList.push({
            id: '',
            specName: '无规格',
            barcode: '',
            costPrice: 0,
            salePrice: 0,
            num: 0,
            status: ''
          })
        } else if (this.dataForm.specList.length === 1) {
          this.dataForm.specList[0].specValue.forEach(item => {
            this.addSku({ specName: (item) })
          })
        } else {
          this.dataForm.specList[0].specValue.forEach(item => {
            this.getSpecValue(0, item)
          })
        }
      },
      getSpecValue (depth, name) {
        depth = depth + 1
        if (depth === this.dataForm.specList.length - 1) {
          this.dataForm.specList[depth].specValue.forEach(item => {
            console.log('---' + depth + '--------->' + (name + ':' + item))
            this.addSku({ specName: (name + ':' + item) })
          })
        } else {
          this.dataForm.specList[depth].specValue.forEach(item => {
            this.getSpecValue(depth, (name + ':' + item))
          })
        }
      },
      inStock () {
      },
      addSku (item) {
        this.dataForm.skuList.push(item)
      },
      delSku (index) {
        this.dataForm.skuList.splice(index, 1)
      }
    }
  }
</script>
