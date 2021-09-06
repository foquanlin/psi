<template>
  <div class="mod-smslog">
    <el-form :inline="true" :model="searchForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="searchForm.sendId" placeholder="发送编号" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.mobile" placeholder="手机号码" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('sys:smslog:config')" type="primary" @click="addConfig()">短信配置</el-button>
        <el-button v-if="isAuth('sys:smslog:send')" type="success" @click="sendSms()">发送短信</el-button>
        <el-button v-if="isAuth('sys:smslog:delete')" type="danger" @click="deleteHandle()"
                   :disabled="dataListSelections.length <= 0">批量删除
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="sendId"
        header-align="center"
        align="center"
        label="发送编号">
      </el-table-column>
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        label="操作人">
        <template slot-scope="scope">
          <span>{{transUser(scope.row.userId)}}</span>
        </template>
      </el-table-column>
      <el-table-column
        show-tooltip-when-overflow
        prop="content"
        header-align="center"
        align="center"
        width="150"
        label="发送内容">
      </el-table-column>
      <el-table-column
        prop="mobile"
        header-align="center"
        align="center"
        label="手机号码">
      </el-table-column>
      <el-table-column
        prop="stime"
        header-align="center"
        align="center"
        label="发送时间">
      </el-table-column>
      <el-table-column
        prop="sign"
        header-align="center"
        align="center"
        width="150"
        label="用户签名">
      </el-table-column>
      <el-table-column
        prop="sendStatus"
        header-align="center"
        align="center"
        label="提交状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sendStatus === 0" size="small">成功</el-tag>
          <el-tag v-else size="small" type="danger">异常</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="invalidNum"
        header-align="center"
        align="center"
        label="无效号码数">
      </el-table-column>
      <el-table-column
        prop="successNum"
        header-align="center"
        align="center"
        label="成功提交数">
      </el-table-column>
      <el-table-column
        prop="blackNum"
        header-align="center"
        align="center"
        label="黑名单数">
      </el-table-column>
      <el-table-column
        prop="returnMsg"
        header-align="center"
        align="center"
        label="返回消息">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('sys:smslog:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 短信配置 -->
    <sms-config v-if="smsConfigVisible" ref="smsConfig" @refreshDataList="getDataList"></sms-config>
    <!-- 发送短信 -->
    <sms-send v-if="smsSendVisible" ref="smsSend" @refreshDataList="getDataList"></sms-send>
  </div>
</template>

<script>
  import SmsConfig from './sms-config'
  import SmsSend from './sms-send'

  export default {
    data () {
      return {
        searchForm: {
          sendId: '',
          mobile: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListSelections: [],
        smsConfigVisible: false,
        smsSendVisible: false
      }
    },
    components: {
      SmsConfig,
      SmsSend
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.$http({
          url: '/sys/smslog/list',
          method: 'get',
          params: {
            page: this.pageIndex,
            limit: this.pageSize,
            sendId: this.searchForm.sendId,
            mobile: this.searchForm.mobile
          }
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.dataList = data.page.records
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
        this.dataListSelections = val
      },
      // 短信配置
      addConfig: function () {
        this.smsConfigVisible = true
        this.$nextTick(() => {
          this.$refs.smsConfig.init()
        })
      },
      // 发送短信
      sendSms: function () {
        this.smsSendVisible = true
        this.$nextTick(() => {
          this.$refs.smsSend.init()
        })
      },
      // 删除
      deleteHandle (id) {
        let ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: '/sys/smslog/delete',
            method: 'post',
            data: ids
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500
              })
              this.getDataList()
            }
          })
        }).catch(() => {
        })
      }
    }
  }
</script>
