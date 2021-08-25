import Vue from 'vue'
import App from '@/App'
import router from '@/router'                 // api: https://github.com/vuejs/vue-router
import store from '@/store'                   // api: https://github.com/vuejs/vuex
import VueCookie from 'vue-cookie'            // api: https://github.com/alfhen/vue-cookie
import '@/element-ui'                         // api: https://github.com/ElemeFE/element
import '@/icons'                              // api: http://www.iconfont.cn/
import '@/element-ui-theme'
import '@/assets/css/common.css'
import '@/assets/scss/index.scss'
import httpRequest from '@/utils/httpRequest' // api: https://github.com/axios/axios
import echarts from 'echarts' // 引入echarts
import {isAuth, treeDataTranslate, transOrg, transUser, transDict} from '@/utils'
import cloneDeep from 'lodash/cloneDeep'
import ElDict from './components/el-dict'
import VueClipboard from 'vue-clipboard2'
import Ueditor from './components/ueditor'
import Moment from 'moment'

Vue.use(VueClipboard)
Vue.use(VueCookie)
Vue.use(ElDict)
Vue.use(Ueditor)

Vue.component('el-dict', ElDict)
Vue.component('ueditor', Ueditor)

Vue.config.productionTip = false
Moment.locale('zh-cn')

// 挂载全局
Vue.prototype.$http = httpRequest // ajax请求方法
Vue.prototype.$echarts = echarts
Vue.prototype.isAuth = isAuth     // 权限方法
Vue.prototype.treeDataTranslate = treeDataTranslate     // 树形数据转换
Vue.prototype.transOrg = transOrg     // 机构翻译
Vue.prototype.transUser = transUser     // 用户翻译
Vue.prototype.transDict = transDict     // 数据字典翻译
Vue.prototype.$moment = Moment    // 时间处理

// 保存整站vuex本地储存初始状态
window.SITE_CONFIG['storeState'] = cloneDeep(store.state)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
