<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="dataForm.userName" placeholder="登录帐号"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-dict code="SEX" v-model="dataForm.sex"></el-dict>
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName">
        <el-input v-model="dataForm.realName" placeholder="真实姓名"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="所属机构" prop="orgNo">
        <el-popover
          ref="orgListPopover"
          placement="top-start"
          trigger="click">
          <el-tree
            :data="orgNoOptions"
            :props="orgListTreeProps"
            node-key="orgNo"
            ref="orgListTree"
            @current-change="orgListTreeCurrentChangeHandle"
            :default-expand-all="true"
            :highlight-current="true"
            :expand-on-click-node="false">
          </el-tree>
        </el-popover>
        <el-input v-model="dataForm.orgName" v-popover:orgListPopover :readonly="true" placeholder="点击选择上级机构"
                  class="org-list__input"></el-input>
      </el-form-item>
      <el-form-item label="角色" prop="roleIdList">
        <el-select v-model="dataForm.roleIdList" multiple clearable filterable placeholder="请选择" class="width100">
          <el-option
            v-for="role in roleList"
            :key="role.roleId"
            :label="role.roleName"
            :value="role.roleId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" size="mini" prop="status">
        <el-radio-group v-model="dataForm.status">
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">正常</el-radio>
        </el-radio-group>
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
        orgNoOptions: this.treeDataTranslate(JSON.parse(sessionStorage.getItem('orgList') || '[]'), 'orgNo', 'parentNo'),
        orgListTreeProps: {
          label: 'orgName',
          children: 'children'
        },
        visible: false,
        roleList: [],
        dataForm: {
          orgName: '',
          id: 0,
          userName: '',
          sex: '',
          realName: '',
          salt: '',
          email: '',
          mobile: '',
          orgNo: '',
          roleIdList: [],
          status: 1
        },
        dataRule: {
          userName: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
          sex: [{required: true, message: '性别不能为空', trigger: 'blur'}],
          realName: [{required: true, message: '真实姓名不能为空', trigger: 'blur'}]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.$http({
          url: '/sys/role/select',
          method: 'get'
        }).then(({data}) => {
          this.roleList = data && data.code === 0 ? data.list : []
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (this.dataForm.id) {
            this.$http({
              url: `/sys/user/info/${this.dataForm.id}`,
              method: 'get'
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userName = data.user.userName
                this.dataForm.sex = data.user.sex.toString()
                this.dataForm.realName = data.user.realName
                this.dataForm.salt = data.user.salt
                this.dataForm.email = data.user.email
                this.dataForm.mobile = data.user.mobile
                this.dataForm.orgNo = data.user.orgNo
                this.dataForm.orgName = this.transOrg(data.user.orgNo)
                this.dataForm.roleIdList = data.user.roleIdList
                this.dataForm.status = data.user.status
              }
            })
          } else {
            this.dataForm.orgName = ''
          }
        })
      },
      // 机构树设置当前选中节点
      orgListTreeSetCurrentNode (type) {
        if (type === 'mod') {
          this.$refs.orgListTree.setCurrentKey(this.dataForm.parentNo)
          this.dataForm.parentName = (this.$refs.orgListTree.getCurrentNode() || {})['orgName']
        }
      },
      // 机构树选中
      orgListTreeCurrentChangeHandle (data) {
        this.dataForm.orgNo = data.orgNo
        this.dataForm.orgName = data.orgName
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: `/sys/user/${!this.dataForm.id ? 'save' : 'update'}`,
              method: 'post',
              data: {
                'userId': this.dataForm.id || undefined,
                'userName': this.dataForm.userName,
                'sex': this.dataForm.sex,
                'realName': this.dataForm.realName,
                'salt': this.dataForm.salt,
                'email': this.dataForm.email,
                'mobile': this.dataForm.mobile,
                'orgNo': this.dataForm.orgNo,
                'status': this.dataForm.status,
                'roleIdList': this.dataForm.roleIdList
              }
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.visible = false
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500
                })
                this.$emit('refreshDataList')
              }
            })
          }
        })
      }
    }
  }
</script>
<style scoped>
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
