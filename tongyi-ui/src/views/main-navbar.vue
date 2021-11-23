<template>
  <nav class="site-navbar" :class="'site-navbar--' + navbarLayoutType">
    <div class="site-navbar__header" v-if="!sidebarFold">
      <h1 class="site-navbar__brand" @click="$router.push({ name: 'home' })">
        <a class="site-navbar__brand-lg" href="javascript:;">酷天软件管理平台</a>
      </h1>
    </div>
    <div class="site-navbar__body clearfix">
      <el-menu
        class="site-navbar__menu"
        mode="horizontal">
        <el-menu-item class="site-navbar__switch" index="0" @click="sidebarFold = !sidebarFold">
          <icon-svg name="zhedie"></icon-svg>
        </el-menu-item>
      </el-menu>
      <el-menu
        class="site-navbar__menu site-navbar__menu--right"
        mode="horizontal">
        <el-menu-item index="3" v-if="isAuth('wx:wxaccount:list')">
          <template slot="title">
            <wx-account-selector></wx-account-selector>
          </template>
        </el-menu-item>
        <el-menu-item index="1">
          <el-badge value="hot">
            <a href="//wxngrok.com/" target="_blank">官方社区</a>
          </el-badge>
        </el-menu-item>
        <el-menu-item index="2" @click="$router.push({ name: 'theme' })">
          <template slot="title">
            <el-badge is-dot value="new">
              <icon-svg name="xitongpeizhi" class="el-icon-setting"></icon-svg>
            </el-badge>
          </template>
        </el-menu-item>
        <el-menu-item class="site-navbar__avatar" index="4">
          <el-dropdown :show-timeout="0" placement="bottom">
            <span class="el-dropdown-link">
              <img src="~@/assets/img/avatar.png" :alt="userName">{{ userName }}
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="updatePasswordHandle()">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logoutHandle()">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu-item>
      </el-menu>
    </div>
    <!-- 弹窗, 修改密码 -->
    <update-password v-if="updatePassowrdVisible" ref="updatePassowrd"></update-password>
  </nav>
</template>

<script>
  import WxAccountSelector from '@/components/wx-account-selector'
  import UpdatePassword from './main-navbar-update-password'
  import {clearLoginInfo} from '@/utils'

  export default {
    data () {
      return {
        updatePassowrdVisible: false
      }
    },
    components: {
      UpdatePassword, WxAccountSelector
    },
    computed: {
      navbarLayoutType: {
        get () {
          return this.$store.state.common.navbarLayoutType
        }
      },
      sidebarFold: {
        get () {
          return this.$store.state.common.sidebarFold
        },
        set (val) {
          this.$store.commit('common/updateSidebarFold', val)
        }
      },
      mainTabs: {
        get () {
          return this.$store.state.common.mainTabs
        },
        set (val) {
          this.$store.commit('common/updateMainTabs', val)
        }
      },
      userName: {
        get () {
          return this.$store.state.user.name
        }
      }
    },
    methods: {
      // 修改密码
      updatePasswordHandle () {
        this.updatePassowrdVisible = true
        this.$nextTick(() => {
          this.$refs.updatePassowrd.init()
        })
      },
      // 退出
      logoutHandle () {
        this.$confirm(`确定进行[退出]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: '/sys/logout',
            method: 'post'
          }).then(({data}) => {
            if (data && data.code === 0) {
              clearLoginInfo()
              this.$router.push({name: 'login'})
            }
          })
        }).catch(() => {
        })
      }
    }
  }
</script>
