<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="所属分组" prop="groupId" :error="groupWrong">
        <el-select v-model="groupId" placeholder="所属分组" @change="changeGroup">
          <el-option v-for="item in groupDataList" :key="item.value" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="字典名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="字典名称"></el-input>
      </el-form-item>
      <el-form-item label="字典值" prop="value">
        <el-input v-model="dataForm.value" placeholder="字典值"></el-input>
      </el-form-item>
      <el-form-item label="排序号" prop="sort">
        <el-input-number v-model="dataForm.sort" controls-position="right" :min="0" step-strictly></el-input-number>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="dataForm.status">
          <el-radio border :label="1">正常</el-radio>
          <el-radio border :label="0">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input type="textarea" v-model="dataForm.remark" placeholder="备注"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        groupId: '',
        groupWrong: '',
        dataForm: {
          id: '',
          groupId: '',
          name: '',
          value: '',
          sort: '',
          status: 1,
          remark: ''
        },
        groupDataList: [],
        dataRule: {
          name: [
            {
              required: true,
              message: '字典名称不能为空',
              trigger: 'blur'
            }
          ],
          value: [
            {
              required: true,
              message: '字典值不能为空',
              trigger: 'blur'
            }
          ]
        }
      }
    },
    methods: {
      init (id, groupId) {
        this.dataForm.id = id || ''
        this.groupId = groupId || ''
        this.dataForm.groupId = groupId

        this.$http({
          url: '/sys/dictgroup/queryAll',
          method: 'get',
          loading: false
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.groupDataList = data.list
            this.visible = true
            this.$nextTick(() => {
              this.$refs['dataForm'].resetFields()
              if (this.dataForm.id) {
                this.$http({
                  url: `/sys/dict/info/${this.dataForm.id}`,
                  method: 'get'
                }).then(({ data }) => {
                  if (data && data.code === 0) {
                    this.dataForm = data.dict
                  }
                })
              }
            })
          }
        })
      },
      changeGroup: function (value) {
        this.dataForm.groupId = value
        this.groupWrong = ''
      },
      // 表单提交
      dataFormSubmit () {
        if (!this.groupId) {
          this.groupWrong = '请选择分组'
        }
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            if (this.groupId) {
              this.groupWrong = ''
            }
            this.dataForm.groupId = this.groupId
            this.$http({
              url: `/sys/dict/${!this.dataForm.id ? 'save' : 'update'}`,
              method: 'post',
              data: this.dataForm
            }).then(({ data }) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500
                })
                this.visible = false
                this.$emit('refreshDataList')
              }
            })
          }
        })
      }
    }
  }
</script>
