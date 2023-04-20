<template>
  <el-dialog :close-on-click-modal="false" width="71%" :visible.sync="visible">
    <el-descriptions title="商品信息" :column="2" style="margin-left: 10px">
      <el-descriptions-item label="商品名称">{{goods.name}}</el-descriptions-item>
      <el-descriptions-item label="商品分类">{{goods.catalog?goods.catalog.name:''}}</el-descriptions-item>
      <el-descriptions-item label="商品编码">{{goods.no}}</el-descriptions-item>
      <el-descriptions-item label="创建时间">{{goods.createDate}}</el-descriptions-item>
      <el-descriptions-item label="单位">{{goods.unit?goods.unit.name:''}}</el-descriptions-item>
      <el-descriptions-item label="库存">{{goods.warehouseNum}}</el-descriptions-item>
      <el-descriptions-item label="备注" :span="2">{{goods.memo}}</el-descriptions-item>
    </el-descriptions>
    <el-table border :data="dataList">
      <el-table-column prop="catalogId" header-align="center" align="left" label="规格名称">
        <template v-slot="scope">
          <el-select v-model="scope.row.specName" :disabled="!scope.row.edited" placeholder="选择规格或输入自定义规格按回车" style="width:100%" clearable allow-create filterable default-first-option/>
        </template>
      </el-table-column>
      <el-table-column prop="catalogId" header-align="center" align="left" label="规格值">
        <template v-slot="scope">
          <el-select v-model="scope.row.specValues" :disabled="!scope.row.edited" placeholder="输入完成按回车或点击保存可新增多个规格值" style="width:100%" multiple allow-create filterable default-first-option>
            <el-option v-for="(item,idx) in scope.row.specValues" :key="item" :value="item" :label="item"/>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="catalogId" header-align="center" align="left" label="操作" width="150">
        <template v-slot="scope">
          <el-button size="mini" @click="saveHandle(scope.row,scope.$index)" v-if="scope.row.edited">保存</el-button>
          <el-button size="mini" @click="cancelHandle(scope.row,scope.$index)" v-if="scope.row.edited">取消</el-button>
          <el-button size="mini" @click="delSpec(scope.row,scope.$index)" v-if="!scope.row.edited">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button  style="margin-top: 10px;" size="mini" icon="el-icon-plus" @click="addSpec" >添加规格</el-button>
<!--      <el-table border :data="dataList" style="width: 100%;">-->
<!--        <el-table-column prop="specName" header-align="center" align="center" label="规格名称">-->
<!--          <template v-slot="scope">-->
<!--            <el-input v-if="scope.row.modify" v-model="scope.row.specName" placeholder="规格名称"></el-input>-->
<!--            <span v-else>{{scope.row.specName}}</span>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="specValue" header-align="center" align="center" label="规格值">-->
<!--          <template v-slot="scope">-->
<!--            <el-select v-model="scope.row.specValue" :disabled="scope.row.modify" placeholder="规格值" clearable>-->
<!--              <el-option v-for="item in scope.row.specValue.split(',')" :key="item.id" :label="item.name" :value="item.id"/>-->
<!--            </el-select>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">-->
<!--          <template v-slot="scope">-->
<!--            <el-button type="text" size="small" @click="saveHandle(scope.row)">保存</el-button>-->
<!--            <el-button type="text" size="small" @click="editHandle(scope.row)">修改</el-button>-->
<!--            <el-button type="text" size="small" @click="deleteHandle(scope.row)">删除</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--      </el-table>-->
  </el-dialog>
</template>

<script>
  import Options from '../sys/options'
  export default {
    data () {
      return {
        goodsId: '',
        visible: false,
        goods: {},
        dataList: [],
        editVisible: false
      }
    },
    components: {
      Options
    },
    methods: {
      formatBool: (row, column, cellValue, index) => Options.formatArray(Options.yesno, cellValue),
      formatSex: (row, column, cellValue, index) => Options.formatArray(Options.genders, cellValue),
      formatDate: (row, column, cellValue, index) => Options.formatDate(row, column, cellValue, index),
      init (id) {
        this.goodsId = id
        this.goods = {}
        this.dataList = []
        this.visible = true
        this.$http({
          loading: false,
          url: `/psi/goods/info/${this.goodsId}`,
          method: 'get'
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.goods = data.info
          } else {
            this.goods = {}
          }
        })
        this.getDataList()
      },
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/psi/goodsspec/listAll',
          method: 'get',
          params: {
            goodsId: this.goodsId
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.list
            this.dataList.forEach(item => {
              item.specValues = item.specValue.split(',')
            })
          } else {
            this.dataList = []
          }
        })
      },
      saveHandle (spec, index) {
        spec.specValue = spec.specValues.join(',')
        this.$http({
          loading: false,
          url: `/psi/goodsspec/${!spec.id ? 'save' : 'update'}`,
          method: 'post',
          data: spec
        }).then(({data}) => {
          if (data && data.code === 0) {
            spec.edited = false
            this.$forceUpdate()
          }
        })
      },
      // 删除
      delSpec (spec, index) {
        if (spec.id === '') {
          this.dataList.splice(index, 1)
          return
        }
        this.$http({
          loading: false,
          url: `/psi/goodsspec/delete`,
          method: 'post',
          data: [spec.id]
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList.splice(index, 1)
          }
        })
      },
      addSpec () {
        this.dataList.push({
          id: '',
          goodsId: this.goodsId,
          specName: '',
          specValue: '',
          specValues: [],
          edited: true
        })
      },
      editHandle (spec, index) {
        spec.edited = true
        this.$forceUpdate()
      },
      cancelHandle (spec, index) {
        spec.edited = false
        this.dataList.splice(index, 1)
      }
    }
  }
</script>
