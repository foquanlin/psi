<template>
  <el-dialog
    :title="!dataForm.orgNo ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="机构名称" prop="orgName">
        <el-input v-model="dataForm.orgName" placeholder="机构名称"></el-input>
      </el-form-item>
      <el-form-item label="上级机构" prop="parentName">
        <el-popover
          ref="orgListPopover"
          placement="top-start"
          trigger="click">
          <el-tree
            :data="parentOptions"
            :props="orgListTreeProps"
            node-key="orgNo"
            ref="orgListTree"
            @current-change="orgListTreeCurrentChangeHandle"
            :default-expand-all="true"
            :highlight-current="true"
            :expand-on-click-node="false">
          </el-tree>
        </el-popover>
        <el-input v-model="dataForm.parentName" v-popover:orgListPopover :readonly="true" placeholder="点击选择上级机构"
                  class="org-list__input"></el-input>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="dataForm.status">
          <el-radio :label="1" border>正常</el-radio>
          <el-radio :label="0" border>禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="排序号" prop="sort">
        <el-input-number v-model="dataForm.sort"></el-input-number>
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
        testTime: '',
        parentOptions: [],
        orgListTreeProps: {
          label: 'orgName',
          children: 'children'
        },
        visible: false,
        dataForm: {
          orgNo: 0,
          orgName: '',
          parentNo: '',
          parentName: '',
          orgType: '',
          status: 1,
          sort: ''
        },
        dataRule: {
          orgName: [
            {required: true, message: '名称不能为空', trigger: 'blur'}
          ],
          status: [
            {required: true, message: '状态不能为空', trigger: 'blur'}
          ],
          sort: [
            {required: true, message: '排序不能为空', trigger: 'blur'}
          ],
          parentName: [
            {required: true, message: '上级机构不能为空', trigger: 'change'}
          ]
        }
      }
    },
    methods: {
      init (orgNo) {
        this.dataForm.orgNo = orgNo || 0
        this.$http({
          url: '/sys/org/queryAll',
          method: 'get'
        }).then(({data}) => {
          data.list.push({
            'orgNo': '0',
            'orgName': '顶级'
          })
          this.parentOptions = this.treeDataTranslate(data.list, 'orgNo', 'parentNo')
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (!this.dataForm.orgNo) {
            // 新增
            this.orgListTreeSetCurrentNode()
          } else {
            // 修改
            this.$http({
              url: `/sys/org/info/${this.dataForm.orgNo}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm = data.org
                this.orgListTreeSetCurrentNode('mod')
              }
            })
          }
        })
      },
      // 机构树选中
      orgListTreeCurrentChangeHandle (data) {
        this.dataForm.parentNo = data.orgNo
        this.dataForm.parentName = data.orgName
      },
      // 机构树设置当前选中节点
      orgListTreeSetCurrentNode (type) {
        if (type === 'mod') {
          this.$refs.orgListTree.setCurrentKey(this.dataForm.parentNo)
          this.dataForm.parentName = (this.$refs.orgListTree.getCurrentNode() || {})['orgName']
        }
      },
      // 表单提交
      dataFormSubmit () {
        if (!this.dataForm.parentNo) {
          this.dataForm.parentNo = 0
        }
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: `/sys/org/${!this.dataForm.orgNo ? 'save' : 'update'}`,
              method: 'post',
              data: this.dataForm
            }).then(({data}) => {
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

<style lang="scss">
  .mod-org {

    .org-list__input,
    .icon-list__input {

      > .el-input__inner {
        cursor: pointer;
      }

    }
    &
    __icon-popover {
      max-width: 350px;
    }

    &
    __icon-list {
      max-height: 380px;
      padding: 0;
      margin: -8px 0 0 -8px;

      > .el-button {
        padding: 8px;
        margin: 8px 0 0 8px;

        > span {
          display: inline-block;
          vertical-align: middle;
          width: 18px;
          height: 18px;
          font-size: 18px;
        }

      }
    }
    .icon-list__tips {
      font-size: 18px;
      text-align: center;
      color: #e6a23c;
      cursor: pointer;
    }

  }
</style>
