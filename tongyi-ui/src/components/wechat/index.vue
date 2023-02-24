<template>
  <div class="menu_setting_box"
       style="display: block;">
    <div id="menustatus_1"
         class="highlight_box border icon_wrap menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon icon_msg_small success"/>
      <p class="title">菜单使用中</p>
      <p class="desc">可在手机查看菜单内容。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_2"
         class="highlight_box border icon_wrap menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon_msg_small info"/>
      <p class="title">菜单编辑中</p>
      <p class="desc">菜单未发布，请确认菜单编辑完成后点击“保存并发布”同步到手机。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_3"
         class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon_msg_small waiting"/>
      <p class="title">菜单已发布</p>
      <p class="desc"><span class="js_waitHour"/>小时后可在手机查看菜单内容。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_4"
         class="highlight_box icon_wrap border icon_small menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon lock"/>
      <p class="title">未开启自定义菜单</p>
      <p class="desc">通过编辑和发布自定义菜单来进行便携管理。若开启菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_5"
         class="highlight_box icon_wrap border icon_small menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon lock"/>
      <p class="title">未开启自定义菜单</p>
      <p class="desc">由于开发者通过接口修改了菜单配置，当前菜单配置已失效并停用。你可以前往<a
        href="/advanced/advanced?action=dev&amp;t=advanced/dev&amp;token=1849635325&amp;lang=zh_CN">开发者中心</a>进行停用。
      </p>
    </div>
    <div id="menustatus_6"
         class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon icon_msg_small success"/>
      <p class="title">API版本菜单使用中</p>
      <p class="desc">该页面显示的菜单版本已失效。当前生效版本请调用API查看。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_7"
         class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon icon_msg_small success"/>
      <p class="title">API版本/个性化菜单使用中</p>
      <p class="desc">该页面显示的菜单版本已失效。当前生效版本请调用API查看。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_8"
         class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon_msg_small waiting"/>
      <p class="title">菜单已发布/个性化菜单使用中</p>
      <p class="desc"><span class="js_waitHour"/>小时后可在手机查看菜单内容。当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_9"
         class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon_msg_small info"/>
      <p class="title">菜单编辑中/个性化菜单使用中</p>
      <p class="desc">菜单未发布，请确认菜单编辑完成后点击“保存并发布”同步到手机。当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div id="menustatus_10"
         class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
         style="display: none;">
      <i class="icon icon icon_msg_small success"/>
      <p class="title">菜单使用中/个性化菜单使用中</p>
      <p class="desc">可在手机查看菜单内容，当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
        href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=1849635325&amp;lang=zh_CN"
        class="js_closeMenu">点击这里</a></p>
    </div>
    <div class="page_msg mini plugin_update_tips js_authorized"
         style="display:none;margin:-20px 30px 0 30px">
      <div class="inner group">
        <span class="msg_icon_wrp"><i class="icon_msg_mini info"/></span>
        <div class="msg_content">
          <p>你已授权给<span class="js_auth_name"/>帮助你运营公众号，点击管理<a
            href="/cgi-bin/component_unauthorize?action=list&amp;t=service/auth_plugins&amp;token=1849635325&amp;lang=zh_CN">已授权的第三方平台</a>
          </p>
        </div>
      </div>
    </div>
    <div class="menu_setting_area js_editBox">
      <div class="menu_preview_area">
        <div class="mobile_menu_preview">
          <div class="mobile_hd tc">
            {{ gzh_name }}
          </div>
          <div class="mobile_bd">
            <ul id="menuList"
                class="pre_menu_list grid_line ui-sortable ui-sortable-disabled no_menu"
                style="z-index:3;">
              <li v-for="(btn,index) in menu.button"
                  :key="btn.id"
                  class="jsMenu pre_menu_item grid_item jslevel1 size1of3 ui-sortable ui-sortable-disabled">
                <a :class="[{pre_menu_link: index===activeMenuIndex && activeMenuType()===1}]"
                   href="javascript:void(0);"
                   draggable="false"
                   @click="menu_selected(btn.name,index)">
                  <i class="icon_menu_dot js_icon_menu_dot dn"/>
                  <i class="icon20_common sort_gray"/>
                  <span class="js_l1Title">{{ btn.name }}</span>
                </a>
                <div v-if="index===activeMenuIndex"
                     class="sub_pre_menu_box js_l2TitleBox">
                  <ul class="sub_pre_menu_list">
                    <li v-for="(sub,index) in btn.subButtons"
                        :key="sub.id"
                        class="jslevel2"><a
                      :class="[{pre_menu_link: index===activeMenuItemIndex && activeMenuType()===2}]"
                      href="javascript:void(0);"
                      class="jsSubView"
                      draggable="false"
                      @click="menu_item_selected(sub.name,index)"><span
                      class="sub_pre_menu_inner js_sub_pre_menu_inner"><i
                      class="icon20_common sort_gray"/><span class="js_l2Title">{{ sub.name }}</span></span></a></li>
                    <li
                      v-if="isSet(menu.button[activeMenuIndex].subButtons)&&btn.subButtons.length>=0&&btn.subButtons.length<5"
                      class="js_addMenuBox">
                      <a href="javascript:void(0);"
                         class="jsSubView js_addL2Btn"
                         title="最多添加5个子菜单"
                         draggable="false"
                         @click="menu_item_add()"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i
                        class="icon14_menu_add"/></span></a></li>
                  </ul>
                  <i class="arrow arrow_out"/>
                  <i class="arrow arrow_in"/>
                </div>
              </li>
              <li class="js_addMenuBox pre_menu_item grid_item no_extra size1of1"><a
                v-if="isSet(menu.button)?(menu.button.length>=0&&menu.button.length<3):false"
                :class="[{pre_menu_link: activeMenuIndex===''}]"
                href="javascript:void(0);"
                class="js_addL1Btn"
                title="最多添加3个一级菜单"
                draggable="false"
                @click="menu_add()"> <i
                class="icon14_menu_add"/> <span class="js_addMenuTips">添加菜单</span></a></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="menu_form_area">
        <div id="js_none"
             class="menu_initial_tips tips_global"
             style="display: none;"/>
        <div v-show="activeMenuIndex > 0 || activeMenuIndex === 0 || activeMenuItemIndex > 0 || activeMenuIndex === 0 "
             id="js_rightBox"
             class="portable_editor to_left"
             style="display: none;">
          <div class="editor_inner">
            <div class="global_mod float_layout menu_form_hd js_second_title_bar">
              <div class="global_info"> 菜单名称</div>
              <div class="global_extra">
                <a v-if="showDelBtnType===1"
                   id="jsDelBt"
                   href="javascript:void(0);"
                   @click="menu_del()">删除菜单</a>
                <a v-if="showDelBtnType===2"
                   id="jsDelBt"
                   href="javascript:void(0);"
                   @click="menu_item_del()">删除子菜单</a>
              </div>
            </div>
            <div id="view"
                 class="menu_form_bd">
              <div id="js_innerNone"
                   style="display:none;"
                   class="msg_sender_tips tips_global"/>
              <div class="frm_control_group js_setNameBox">
                <label class="frm_label"> <strong class="title js_menuTitle">菜单名称</strong>
                </label>
                <div class="frm_controls">
                  <span v-if="activeMenuType() === 1 && isSet(menu.button[activeMenuIndex])"
                        class="frm_input_box with_counter counter_in append"> <input
                    v-model="menu.button[activeMenuIndex].name"
                    type="text"
                    class="frm_input js_menu_name"> </span>
                  <span
                    v-if="activeMenuType() === 2 && isSet(menu.button[activeMenuIndex].subButtons[activeMenuItemIndex])"
                    class="frm_input_box with_counter counter_in append">
                    <input v-model="menu.button[activeMenuIndex].subButtons[activeMenuItemIndex].name"
                           type="text"
                           class="frm_input js_menu_name">
                  </span>
                  <p class="frm_msg fail js_titleEorTips dn">字数超过上限</p>
                  <p class="frm_msg fail js_titlenoTips dn"
                     style="display: none;">请输入菜单名称</p>
                  <p class="frm_tips js_titleNolTips">字数不超过4个汉字或8个字母</p>
                </div>
              </div>
              <div
                v-show="((activeMenuIndex > 0 || activeMenuIndex === 0) && (menu.button[activeMenuIndex].subButtons.length === 0)) || (activeMenuItemIndex > 0 || activeMenuItemIndex === 0) "
                class="frm_control_group"
                style="display: none;">
                <label class="frm_label"> <strong class="title js_menuContent">菜单内容</strong>
                </label>
                <div class="frm_controls frm_vertical_pt">
                  <label :class="[{selected:showMenuContentType===1}]"
                         class="frm_radio_label js_radio_sendMsg" data-editing="0"
                         @click="radio_label_selected(1)"> <i class="icon_radio"/> <span class="lbl_content">发送消息</span>
                    <input type="radio" name="hello" class="frm_radio"> </label>
                  <label :class="[{selected:showMenuContentType===2}]"
                         class="frm_radio_label js_radio_url"
                         data-editing="0"
                         @click="radio_label_selected(2)"> <i class="icon_radio"/> <span class="lbl_content">跳转网页</span>
                    <input type="radio"
                           name="hello"
                           class="frm_radio"> </label>
                  <label :class="[{selected:showMenuContentType===3}]"
                         class="frm_radio_label js_radio_url"
                         data-editing="0"
                         @click="radio_label_selected(3)"> <i class="icon_radio"/> <span class="lbl_content">小程序</span>
                    <input type="radio"
                           name="hello"
                           class="frm_radio"> </label>
                </div>
              </div>
              <div class="menu_content_container">
                <div v-show="showMenuContentType===1"
                     id="edit" class="menu_content send jsMain"
                     style="display: none;">
                  <div id="editDiv" class="msg_sender">
                    <div class="msg_tab">
                      <div class="tab_navs_panel">
                        <span class="tab_navs_switch_wrp switch_prev js_switch_prev"> <span
                          class="tab_navs_switch"/> </span>
                        <span
                          class="tab_navs_switch_wrp switch_next js_switch_next"
                          style="display: none;"> <span class="tab_navs_switch"/>
                        </span>
                        <div class="tab_navs_wrp"
                             style="width: 650px">
                          <ul class="tab_navs js_tab_navs" style="margin-left:0;">
                            <li :class="[{selected:showMenuContentMsgType===1}]"
                                class="tab_nav tab_appmsg width4"
                                data-type="10"
                                data-tab=".js_appmsgArea"
                                data-tooltip="图文消息"><a
                              href="javascript:void(0);"
                              @click="content_msg_tab_nav(1)">&nbsp;<i
                              class="icon_msg_sender"/><span class="msg_tab_title">图文消息</span></a></li>
                            <li :class="[{selected:showMenuContentMsgType===2}]"
                                class="tab_nav tab_img width4"
                                data-type="2"
                                data-tab=".js_imgArea"
                                data-tooltip="图片"><a
                              href="javascript:void(0);"
                              @click="content_msg_tab_nav(2)">&nbsp;<i
                              class="icon_msg_sender"/><span class="msg_tab_title">图片</span></a></li>
                            <li :class="[{selected:showMenuContentMsgType===3}]"
                                class="tab_nav tab_audio width4"
                                data-type="3"
                                data-tab=".js_audioArea"
                                data-tooltip="语音"><a
                              href="javascript:void(0);"
                              @click="content_msg_tab_nav(3)">&nbsp;<i
                              class="icon_msg_sender"/><span class="msg_tab_title">语音</span></a></li>
                            <li :class="[{selected:showMenuContentMsgType===4}]"
                                class="tab_nav tab_video width4 no_extra"
                                data-type="15"
                                data-tab=".js_videoArea"
                                data-tooltip="视频">
                              <a href="javascript:void(0);"
                                 @click="content_msg_tab_nav(4)">&nbsp;<i class="icon_msg_sender"/><span
                                class="msg_tab_title">视频</span></a></li>
                            <li class="tab_nav">
                              <label>KEY:</label>
                              <input v-if="activeMenuType() === 1 && isSet(menu.button[activeMenuIndex])"
                                     v-model="menu.button[activeMenuIndex].key"
                                     style="width: 190px"
                                     type="text"
                                     value="">
                              <input
                                v-if="activeMenuType() === 2 && isSet(menu.button[activeMenuIndex].subButtons[activeMenuItemIndex])"
                                v-model="menu.button[activeMenuIndex].subButtons[activeMenuItemIndex].key"
                                style="width: 190px"
                                type="text"
                                value="">
                            </li>
                          </ul>
                        </div>
                      </div>
                      <div class="tab_panel">
                        <div v-show="showMenuContentMsgType===1" class="tab_content" style="display: none;">
                          <div class="js_appmsgArea inner ">
                            <!--type 10图文 2图片  3语音 15视频 11商品消息-->
                            <div class="tab_cont_cover jsMsgSendTab" data-index="0">
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  class="add_gray_wrp jsMsgSenderPopBt"
                                  href="javascript:void(0);"
                                  data-type="10"
                                  data-index="0"> <i class="icon36_common add_gray"/>
                                <strong>从素材库中选择</strong> </a> </span>
                              </div>
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  target="_blank"
                                  class="add_gray_wrp"
                                  href="javascript:void(0);">
                                  <i class="icon36_common add_gray"/>
                                <strong>新建图文消息</strong> </a>
                                  <!--
                               <a target="_blank" href="javascript:void(0);"><i class="icon_shopmsg_create"></i><strong>单图文消息</strong></a>
                               <a target="_blank" href="javascript:void(0);"><i class="icon_shopmsg_create multi"></i><strong>多图文消息</strong></a>
                               -->
                                </span>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div v-show="showMenuContentMsgType===2"
                             class="tab_content" style="display: none;">
                          <div class="js_imgArea inner ">
                            <!--type 10图文 2图片  3语音 15视频 11商品消息-->
                            <div class="tab_cont_cover jsMsgSendTab" data-index="1" data-type="2">
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  class="add_gray_wrp jsMsgSenderPopBt"
                                  href="javascript:void(0);"
                                  data-type="2"
                                  data-index="1"> <i class="icon36_common add_gray"/>
                                <strong>从素材库中选择</strong> </a> </span>
                              </div>
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  id="msgSendImgUploadBt"
                                  class="add_gray_wrp"
                                  data-type="2"
                                  href="javascript:void(0);"> <i
                                  class="icon36_common add_gray"/>
                                <strong>上传图片</strong> </a> </span>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div v-show="showMenuContentMsgType===3" class="tab_content" style="display: none;">
                          <div class="js_audioArea inner ">
                            <!--type 10图文 2图片  3语音 15视频 11商品消息-->
                            <div class="tab_cont_cover jsMsgSendTab" data-index="2" data-type="3">
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  class="add_gray_wrp jsMsgSenderPopBt"
                                  href="javascript:void(0);"
                                  data-type="3"
                                  data-index="2"> <i class="icon36_common add_gray"/>
                                <strong>从素材库中选择</strong> </a> </span>
                              </div>
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  id="msgSendAudioUploadBt"
                                  class="add_gray_wrp "
                                  href="javascript:void(0);"> <i
                                  class="icon36_common add_gray"/>
                                <strong>新建语音</strong> </a> </span>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div v-show="showMenuContentMsgType===4" class="tab_content" style="display: none;">
                          <div class="js_videoArea inner ">
                            <!--type 10图文 2图片  3语音 15视频 11商品消息-->
                            <div class="tab_cont_cover jsMsgSendTab" data-index="3">
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  class="add_gray_wrp jsMsgSenderPopBt"
                                  href="javascript:void(0);"
                                  data-type="15"
                                  data-index="3"> <i class="icon36_common add_gray"/>
                                <strong>从素材库中选择</strong> </a> </span>
                              </div>
                              <div class="media_cover">
                                <span class="create_access"> <a
                                  target="_blank"
                                  class="add_gray_wrp"
                                  href="javascript:void(0);">
                                  <i class="icon36_common add_gray"/>
                                <strong>新建视频</strong> </a> </span>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <p class="profile_link_msg_global menu_send mini_tips warn dn js_warn">
                    请勿添加其他公众号的主页链接 </p>
                </div>
                <div v-show="showMenuContentType===2" id="url"
                     class="menu_content url jsMain" style="display: none;">
                  <form id="urlForm" action="" onsubmit="return false;">
                    <p class="menu_content_tips tips_global">订阅者点击该子菜单会跳到以下链接</p>
                    <div class="frm_control_group">
                      <label class="frm_label">页面地址</label>
                      <div class="frm_controls">
                        <span v-if="activeMenuType() === 1" class="frm_input_box">
                          <input id="urlText"
                                 v-model="menu.button[activeMenuIndex].url"
                                 type="text"
                                 class="frm_input"
                                 name="urlText"> </span>
                        <span v-if="activeMenuType() === 2" class="frm_input_box">
                          <input id="urlText"
                                 v-model="menu.button[activeMenuIndex].subButtons[activeMenuItemIndex].url"
                                 type="text"
                                 class="frm_input"
                                 name="urlText">
                        </span>
                        <p class="profile_link_msg_global menu_url mini_tips warn dn js_warn">
                          请勿添加其他公众号的主页链接 </p>
                        <p id="js_urlTitle" class="frm_tips"
                           style="display: none;"> 来自<span class="js_name"/><span style="display:none;"> -《<span
                          class="js_title"/>》</span></p>
                      </div>
                    </div>
                    <div class="frm_control_group btn_appmsg_wrap">
                      <div class="frm_controls">
                        <p id="urlUnSelect" class="frm_msg fail dn" style="display: none;">
                          <span for="urlText" class="frm_msg_content"
                                style="display: inline;">请选择一篇文章</span></p>
                        <a id="js_appmsgPop" href="javascript:void(0);">从公众号图文消息中选择</a>
                        <el-button type="primary" id="js_reChangeAppmsg">重新选择</el-button>
                      </div>
                    </div>
                  </form>
                </div>
                <div v-show="showMenuContentType===3"
                     class="menu_content url jsMain"
                     style="display: none;">
                  <form action="" onsubmit="return false;">
                    <p class="menu_content_tips tips_global">订阅者点击该子菜单会跳到以下小程序</p>
                    <div class="frm_control_group">
                      <label class="frm_label">小程ID</label>
                      <div class="frm_controls">
                        <span v-if="activeMenuType() === 1"
                              class="frm_input_box"> <input
                          id="urlText"
                          v-model="menu.button[activeMenuIndex].appId"
                          type="text"
                          class="frm_input"
                          name="urlText"> </span>
                        <span v-if="activeMenuType() === 2"
                              class="frm_input_box"> <input
                          id="urlText"
                          v-model="menu.button[activeMenuIndex].subButtons[activeMenuItemIndex].appId"
                          type="text"
                          class="frm_input"
                          name="urlText">
                        </span>
                        <p class="profile_link_msg_global menu_url mini_tips warn dn js_warn">
                          请勿添加其他公众号的主页链接 </p>
                        <p id="js_urlTitle" class="frm_tips" style="display: none;"> 来自<span class="js_name"/><span
                          style="display:none;"> -《<span
                          class="js_title"/>》</span></p>
                      </div>
                    </div>
                    <div class="frm_control_group">
                      <label class="frm_label">首页地址</label>
                      <div class="frm_controls">
                        <span v-if="activeMenuType() === 1"
                              class="frm_input_box"> <input
                          v-model="menu.button[activeMenuIndex].pagePath"
                          type="text"
                          class="frm_input"
                          name="urlText"> </span>
                        <span v-if="activeMenuType() === 2"
                              class="frm_input_box"> <input
                          v-model="menu.button[activeMenuIndex].subButtons[activeMenuItemIndex].pagePath"
                          type="text"
                          class="frm_input"
                          name="urlText">
                        </span>
                        <p class="profile_link_msg_global menu_url mini_tips warn dn js_warn">
                          请勿添加其他公众号的主页链接 </p>
                        <p id="js_urlTitle"
                           class="frm_tips"
                           style="display: none;"> 来自<span class="js_name"/><span style="display:none;"> -《<span
                          class="js_title"/>》</span></p>
                      </div>
                    </div>
                    <div class="frm_control_group">
                      <label class="frm_label">页面地址</label>
                      <div class="frm_controls">
                        <span v-if="activeMenuType() === 1" class="frm_input_box">
                          <input id="urlText"
                                 v-model="menu.button[activeMenuIndex].url"
                                 type="text"
                                 class="frm_input"
                                 name="urlText"> </span>
                        <span v-if="activeMenuType() === 2" class="frm_input_box">
                          <input id="urlText"
                                 v-model="menu.button[activeMenuIndex].subButtons[activeMenuItemIndex].url"
                                 type="text"
                                 class="frm_input"
                                 name="urlText">
                        </span>
                        <p class="profile_link_msg_global menu_url mini_tips warn dn js_warn">
                          请勿添加其他公众号的主页链接 </p>
                        <p id="js_urlTitle" class="frm_tips"
                           style="display: none;"> 来自<span class="js_name"/><span style="display:none;"> -《<span
                          class="js_title"/>》</span></p>
                      </div>
                    </div>
                  </form>
                </div>
                <div class="menu_content sended" style="display:none;">
                  <p class="menu_content_tips tips_global">订阅者点击该子菜单会跳到以下链接</p>
                  <div id="viewDiv" class="msg_wrp"/>
                  <p class="frm_tips">来自<span class="js_name">素材库</span><span style="display:none;">
                    -《<span class="js_title"/>》</span></p>
                </div>
                <div id="js_errTips"
                     style="display:none;"
                     class="msg_sender_msg mini_tips warn"/>
              </div>
            </div>
          </div>
          <span class="editor_arrow_wrp"> <i class="editor_arrow editor_arrow_out"/>
            <i class="editor_arrow editor_arrow_in"/> </span>
        </div>
      </div>
    </div>
    <div class="tool_bar tc js_editBox">
      <el-button type="primary" @click="saveData()">保存菜单</el-button>
      <el-button type="danger" @click="delData()">删除菜单</el-button>
      <el-button type="success" @click="pubData()">发布菜单</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Wechat',
    props: {
      data: {
        type: Object,
        default: () => {
          return {}
        }
      }
    },
    data: function () {
      return {
        gzh_name: '实时预览',
        menu: {},
        new_menu: {button: []},
        activeMenuName: '',
        activeMenuIndex: '',
        activeMenuItemName: '',
        activeMenuItemIndex: '',
        showDelBtnType: '', // 1:delMenu 2:delMenuItem
        showMenuContentType: '', // 1:发送消息 2:跳转链接
        showMenuContentMsgType: '' // 1:图文信息 2:图片 3:语音 4:视频
      }
    },
    watch: {
      data: {
        handler (val) {
          this.menu = val
        },
        immediate: true
      }
    },
    methods: {
      menu_data_completing: function () {
        for (let i = 0; i < this.menu.button.length; i++) {
          if (!('type' in this.menu.button[i])) {
            this.menu.button[i].type = 'click'
          }
          if (!('name' in this.menu.button[i])) {
            this.menu.button[i].name = ''
          }
          if (!('key' in this.menu.button[i])) {
            this.menu.button[i].key = ''
          }
          if (!('url' in this.menu.button[i])) {
            this.menu.button[i].url = ''
          }
          if (!('appId' in this.menu.button[i])) {
            this.menu.button[i].appId = ''
          }
          if (!('pagePath' in this.menu.button[i])) {
            this.menu.button[i].pagePath = ''
          }
          if (!('subButtons' in this.menu.button[i])) {
            this.menu.button[i].subButtons = []
          }
          if (this.menu.button[i].subButtons.length > 0) {
            for (let j = 0; j < this.menu.button[i].subButtons.length; j++) {
              if (!('type' in this.menu.button[i].subButtons[j])) {
                this.menu.button[i].subButtons[j].type = 'click'
              }
              if (!('name' in this.menu.button[i].subButtons[j])) {
                this.menu.button[i].subButtons[j].name = ''
              }
              if (!('key' in this.menu.button[i].subButtons[j])) {
                this.menu.button[i].subButtons[j].key = ''
              }
              if (!('url' in this.menu.button[i].subButtons[j])) {
                this.menu.button[i].subButtons[j].url = ''
              }
              if (!('appId' in this.menu.button[i].subButtons[j])) {
                this.menu.button[i].subButtons[j].appId = ''
              }
              if (!('pagePath' in this.menu.button[i].subButtons[j])) {
                this.menu.button[i].subButtons[j].pagePath = ''
              }
              if (!('subButtons' in this.menu.button[i].subButtons[j])) {
                this.menu.button[i].subButtons[j].subButtons = []
              }
            }
          }
        }
      },
      saveData: function () {
        // 补全数据,无数据也要为空
        this.menu_data_completing()
        for (let i = 0; i < this.menu.button.length; i++) {
          if (this.menu.button[i].subButtons.length > 0) {
            const _subButton = []
            for (let j = 0; j < this.menu.button[i].subButtons.length; j++) {
              if (this.menu.button[i].subButtons[j].type === 'click') {
                _subButton[j] = {
                  type: this.menu.button[i].subButtons[j].type,
                  name: this.menu.button[i].subButtons[j].name,
                  key: this.menu.button[i].subButtons[j].key
                }
              } else if (this.menu.button[i].subButtons[j].type === 'view') {
                _subButton[j] = {
                  type: this.menu.button[i].subButtons[j].type,
                  name: this.menu.button[i].subButtons[j].name,
                  url: this.menu.button[i].subButtons[j].url
                }
              } else if (this.menu.button[i].subButtons[j].type === 'miniprogram') {
                _subButton[j] = {
                  type: this.menu.button[i].subButtons[j].type,
                  name: this.menu.button[i].subButtons[j].name,
                  appId: this.menu.button[i].subButtons[j].appId,
                  pagePath: this.menu.button[i].subButtons[j].pagePath,
                  url: this.menu.button[i].subButtons[j].url
                }
              }
            }
            if (this.menu.button[i].type === 'click') {
              this.new_menu.button[i] = {
                name: this.menu.button[i].name,
                subButtons: _subButton
              }
            } else if (this.menu.button[i].type === 'view') {
              this.new_menu.button[i] = {
                name: this.menu.button[i].name,
                subButtons: _subButton
              }
            } else if (this.menu.button[i].type === 'miniprogram') {
              this.new_menu.button[i] = {
                name: this.menu.button[i].name,
                subButtons: _subButton
              }
            }
          } else {
            if (this.menu.button[i].type === 'click') {
              this.new_menu.button[i] = {
                type: this.menu.button[i].type,
                name: this.menu.button[i].name,
                key: this.menu.button[i].key
              }
            } else if (this.menu.button[i].type === 'view') {
              this.new_menu.button[i] = {
                type: this.menu.button[i].type,
                name: this.menu.button[i].name,
                url: this.menu.button[i].url
              }
            } else if (this.menu.button[i].type === 'miniprogram') {
              this.new_menu.button[i] = {
                type: this.menu.button[i].type,
                name: this.menu.button[i].name,
                appId: this.menu.button[i].appId,
                pagePath: this.menu.button[i].pagePath,
                url: this.menu.button[i].url
              }
            }
          }
        }
        this.$emit('saveData', this.new_menu)
      },
      pubData: function () {
        this.$emit('pubData', this.new_menu)
      },
      delData: function () {
        this.$emit('delData', this.new_menu)
      },
      menu_selected: function (name, index) {
        this.showDelBtnType = 1
        this.activeMenuName = name
        this.activeMenuIndex = index
        this.activeMenuItemName = ''
        this.activeMenuItemIndex = ''

        // 补全数据,无数据也要为空
        this.menu_data_completing()

        if (this.menu.button[this.activeMenuIndex].subButtons.length > 0) {
          this.showMenuContentType = ''
        } else {
          this.showMenuContentType = this.activeMenuBtnType()
        }
      },
      menu_item_selected: function (name, index) {
        this.showDelBtnType = 2
        this.activeMenuItemName = name
        this.activeMenuItemIndex = index

        // 补全数据,无数据也要为空
        this.menu_data_completing()

        if (this.activeMenuIndex > 0 || this.activeMenuIndex === 0) {
          if (this.menu.button[this.activeMenuIndex].subButtons.length > 0) {
            this.showMenuContentType = this.activeMenuBtnType()
          }
        }
      },
      menu_add: function () {
        if (this.menu.button.length < 3) {
          this.activeMenuItemIndex = ''
          this.activeMenuItemName = ''
          this.menu.button.push({
            type: 'click',
            name: '菜单名称',
            key: '',
            url: '',
            subButtons: []
          })
          this.activeMenuIndex = this.menu.button.length - 1
          this.activeMenuName = '菜单名称'
          this.showDelBtnType = 1
          // 补全数据,无数据也要为空
          this.menu_data_completing()
        } else {
          alert('最多3个一级菜单')
        }
        this.radio_label_selected(1)
      },
      menu_item_add: function () {
        if (this.menu.button[this.activeMenuIndex].subButtons.length < 5) {
          this.menu.button[this.activeMenuIndex].subButtons.push({
            type: 'click',
            name: '子菜单名称',
            key: '',
            url: '',
            subButtons: []
          })
          this.activeMenuItemIndex =
            this.menu.button[this.activeMenuIndex].subButtons.length - 1
          this.activeMenuItemName = '子菜单名称'
          this.showDelBtnType = 2
          // 补全数据,无数据也要为空
          this.menu_data_completing()
        } else {
          alert('最多5个二级菜单')
        }
      },
      menu_del: function () {
        if (this.menu.button.length <= 3 && this.menu.button.length > 0) {
          if (
            this.activeMenuIndex !== 0 &&
            this.activeMenuIndex === this.menu.button.length - 1
          ) {
            this.menu.button.splice(this.activeMenuIndex, 1)
            this.activeMenuIndex -= 1
          } else if (this.activeMenuIndex === 0) {
            this.menu.button.splice(this.activeMenuIndex, 1)
            this.activeMenuIndex = 0
          } else {
            this.menu.button.splice(this.activeMenuIndex, 1)
          }

          if (this.menu.button.length === 0) {
            this.activeMenuIndex = ''
            this.activeMenuName = ''
            this.activeMenuItemIndex = ''
            this.activeMenuItemName = ''
          }
        }
      },
      menu_item_del: function () {
        if (this.menu.button[this.activeMenuIndex].subButtons.length <= 5 &&
          this.menu.button[this.activeMenuIndex].subButtons.length > 0) {
          if (this.activeMenuItemIndex ===
            this.menu.button[this.activeMenuIndex].subButtons.length - 1) {
            this.menu.button[this.activeMenuIndex].subButtons.splice(
              this.activeMenuItemIndex,
              1
            )
            this.activeMenuItemIndex -= 1
          } else if (this.activeMenuItemIndex === 0) {
            this.menu.button[this.activeMenuIndex].subButtons.splice(
              this.activeMenuItemIndex,
              1
            )
            this.activeMenuItemIndex = 0
          } else {
            this.menu.button[this.activeMenuIndex].subButtons.splice(
              this.activeMenuItemIndex,
              1
            )
          }

          if (this.menu.button[this.activeMenuIndex].subButtons.length === 0) {
            this.activeMenuIndex = ''
            this.activeMenuName = ''
            this.activeMenuItemIndex = ''
            this.activeMenuItemName = ''
          }
          this.showMenuContentType = this.activeMenuBtnType()
        }
      },
      radio_label_selected: function (val) {
        this.showMenuContentType = val
        if (val === 1) {
          this.setType('click')
        } else if (val === 2) {
          this.setType('view')
        } else if (val === 3) {
          this.setType('miniprogram')
        }
      },
      content_msg_tab_nav: function (val) {
        this.showMenuContentMsgType = val
      },
      activeMenuType: function () {
        if (this.activeMenuIndex !== '' && this.activeMenuItemIndex !== '') {
          // 子菜单、二级菜单
          return 2
        } else if (
          this.activeMenuIndex !== '' &&
          this.activeMenuItemIndex === ''
        ) {
          // 一级菜单
          return 1
        } else {
          return 0
        }
      },
      activeMenuBtnType: function () {
        if (this.activeMenuType() === 1) {
          // 一级菜单
          switch (this.menu.button[this.activeMenuIndex].type) {
            case 'click':
              return 1
            case 'view':
              return 2
            case 'miniprogram':
              return 3
            default:
              return 0
          }
        } else if (this.activeMenuType() === 2) {
          // 子菜单、二级菜单
          switch (
            this.menu.button[this.activeMenuIndex].subButtons[this.activeMenuItemIndex].type) {
            case 'click':
              return 1
            case 'view':
              return 2
            case 'miniprogram':
              return 3
            default:
              return 0
          }
        } else {
          return ''
        }
      },
      isSet: function (variable) {
        if (typeof variable !== 'undefined') {
          return true
        } else {
          return false
        }
      },
      setType: function (type) {
        if (this.activeMenuType() === 1) {
          this.menu.button[this.activeMenuIndex].type = type
        } else if (this.activeMenuType() === 2) {
          this.menu.button[this.activeMenuIndex].subButtons[this.activeMenuItemIndex].type = type
        }
      },
      setNemMenuObj: function () {
        for (let i = 0; i < this.menu.button.length; i++) {
          if (this.menu.button[i].subButtons.length > 0) {
            this.new_menu.push({
              name: this.menu.button[i].name,
              subButtons: this.menu.button[i].subButtons
            })
          } else {
            if (this.menu.button[i].type === 'click') {
              this.new_menu.push({
                name: this.menu.button[i].name,
                type: this.menu.button[i].type,
                key: this.menu.button[i].key,
                subButtons: []
              })
            } else if (this.menu.button[i].type === 'view') {
              this.new_menu.push({
                name: this.menu.button[i].name,
                type: this.menu.button[i].type,
                url: this.menu.button[i].url,
                subButtons: []
              })
            } else if (this.menu.button[i].type === 'miniprogram') {
              this.new_menu.push({
                name: this.menu.button[i].name,
                type: this.menu.button[i].type,
                appId: this.menu.button[i].appId,
                pagePath: this.menu.button[i].pagePath,
                url: this.menu.button[i].url,
                subButtons: []
              })
            }
          }
        }
      }
    }
  }
</script>

<style>

  p, ul, ol {
    margin: 0;
    padding-left: 0;
    list-style-type: none
  }

  .frm_control_group {
    *zoom: 1;
    padding-bottom: 25px
  }

  .frm_control_group:after {
    content: "\200B";
    display: block;
    height: 0;
    clear: both
  }

  .frm_control_group.frm_label_block .frm_label {
    display: block;
    width: auto;
    float: none;
    font-size: 14px;
    margin-bottom: .5em
  }

  .grid_line .no_extra.grid_item {
    float: none;
    width: auto;
    overflow: hidden;
    *zoom: 1;
    *overflow: visible
  }

  .size1of1 {
    width: 100%
  }

  .size1of3 {
    width: 33.33%
  }

  .icon_radio {
    background: url(~@/assets/img/base.png) 0 0 no-repeat;
    width: 16px;
    height: 16px;
    vertical-align: middle;
    display: inline-block;
    margin-top: -0.2em;
    *margin-top: 0
  }

  .icon_radio {
    background: url(~@/assets/img/base.png) 0 -140px no-repeat;
    width: 16px;
    height: 16px;
    vertical-align: middle;
    display: inline-block;
    margin-right: 3px;
    margin-top: -2px;
    *margin-top: 0
  }

  .selected .icon_radio {
    background: url(~@/assets/img/base.png) 0 -160px no-repeat
  }

  .disabled .icon_radio {
    background: url(~@/assets/img/base.png) 0 -180px no-repeat
  }

  .selected.disabled .icon_radio {
    background: url(~@/assets/img/base.png) 0 -200px no-repeat
  }

  .menu_setting_box .tool_bar {
    padding-top: 0;
    margin: 14px 30px 0;
  }

  .highlight_box {
    position: relative;
    padding: 9px 30px;
    background-color: #f4f5f9;
    margin-bottom: 20px;
    line-height: 1.6;
    *zoom: 1
  }

  .highlight_box .links .selected a {
    color: #222
  }

  .highlight_box .title {
    font-weight: 400;
    font-style: normal
  }

  .highlight_box .desc {
    color: #8d8d8d;
    overflow: hidden
  }

  .highlight_box.border {
    border-top: 1px solid #e7e7eb
  }

  .highlight_box.icon_wrap {
    padding-top: 14px;
    padding-bottom: 14px;
    overflow: hidden;
    *zoom: 1
  }

  .highlight_box.icon_wrap .icon {
    float: left;
    margin-right: 15px
  }

  .highlight_box.icon_wrap .icon.lock {
    background: url(~@/assets/img/base.png) 0 -336px no-repeat;
    width: 48px;
    height: 48px;
    vertical-align: middle;
    display: inline-block
  }

  .highlight_box.icon_wrap .icon_msg_small {
    margin-top: 5px
  }

  .highlight_box.icon_wrap .title, .highlight_box.icon_wrap .desc {
    overflow: hidden;
    *zoom: 1
  }

  .highlight_box.icon_small .icon {
    margin-left: -10px;
    margin-right: 0
  }

  .highlight_box.single_line .title, .highlight_box.single_line .desc {
    padding-top: 14px
  }

  .icon_msg_sender {
    margin-right: 3px;
    margin-top: -2px;
    *margin-top: 2px;
    width: 20px;
    height: 20px;
    vertical-align: middle;
    display: inline-block
  }

  .tab_text .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 0 no-repeat
  }

  .tab_text.selected .icon_msg_sender, .tab_text:hover .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -30px no-repeat
  }

  .tab_img .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -60px no-repeat
  }

  .tab_img.selected .icon_msg_sender, .tab_img:hover .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -90px no-repeat
  }

  .tab_video .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -120px no-repeat
  }

  .tab_video.selected .icon_msg_sender, .tab_video:hover .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -150px no-repeat
  }

  .tab_audio .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -180px no-repeat
  }

  .tab_audio.selected .icon_msg_sender, .tab_audio:hover .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -210px no-repeat
  }

  .tab_appmsg .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -240px no-repeat
  }

  .tab_appmsg.selected .icon_msg_sender, .tab_appmsg:hover .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -270px no-repeat
  }

  .tab_commondity_appmsg .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -300px no-repeat
  }

  .tab_commondity_appmsg.selected .icon_msg_sender, .tab_commondity_appmsg:hover .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -330px no-repeat
  }

  .tab_cardmsg .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -360px no-repeat
  }

  .tab_cardmsg.selected .icon_msg_sender, .tab_cardmsg:hover .icon_msg_sender {
    background: url(~@/assets/img/msg_tab.png) 0 -390px no-repeat
  }

  .msg_sender .tab_navs {
    background-color: #fff;
    line-height: 38px;
    height: 38px
  }

  .msg_sender .tab_nav.selected .msg_tab_title {
    color: #222
  }

  .msg_sender .tab_nav .msg_tab_title {
    color: #8d8d8d
  }

  .menu_preview_area {
    float: left;
    margin-right: 12px;
    position: relative
  }

  .menu_preview_area .icon14_menu_add {
    background: url(~@/assets/img/index.png) 0 0 no-repeat;
    width: 14px;
    height: 14px;
    vertical-align: middle;
    display: inline-block;
    margin-top: -2px
  }

  .menu_preview_area .pre_menu_list {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    border-top: 1px solid #e7e7eb;
    background: transparent url(~@/assets/img/bg_mobile_foot_default.png) no-repeat 0 0;
    background-position: 0 0;
    background-repeat: no-repeat;
    padding-left: 43px
  }

  .menu_preview_area .pre_menu_list .sort_gray {
    display: none
  }

  .menu_preview_area .pre_menu_list.no_menu .icon14_menu_add {
    background: url(~@/assets/img/index.png) 0 -18px no-repeat
  }

  .menu_preview_area .pre_menu_list.sorting .sort_gray {
    display: inline-block
  }

  .menu_preview_area .pre_menu_list.sorting .icon_menu_dot {
    display: none
  }

  .sub_pre_menu_list li {
    line-height: 30px
  }

  .sub_pre_menu_list li a {
    padding: 0 .5em
  }

  .menu_preview_area .pre_menu_list.sorting .sub_pre_menu_list li.current a {
    color: #616161
  }

  .menu_preview_area .pre_menu_list.sorting .sub_pre_menu_list li.current .sub_pre_menu_inner {
    border-top-width: 1px
  }

  .menu_preview_area .pre_menu_list .sort_gray {
    margin-top: -4px
  }

  .menu_preview_area .pre_menu_item {
    border: 0 1px solid #d0d0d0;
    line-height: 50px
  }

  .menu_preview_area .sub_pre_menu_box {
    bottom: 60px;
    background-color: #fafafa;
    border-top-width: 0
  }

  .menu_preview_area .sub_pre_menu_inner {
    display: block;
    border-top: 1px solid #e7e7eb;
    width: auto;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    word-wrap: normal;
    cursor: pointer
  }

  .menu_preview_area .sub_pre_menu_list li {
    line-height: 44px;
    border: 1px solid transparent;
    margin: 0 -1px -1px
  }

  .menu_preview_area .sub_pre_menu_list li:first-child {
    border-top: 1px solid #d0d0d0
  }

  .menu_preview_area .sub_pre_menu_list li:first-child .sub_pre_menu_inner {
    border-top-width: 0
  }

  .menu_preview_area .sub_pre_menu_list li:hover {
    background-color: #eee;
    border: 1px solid #d0d0d0;
    line-height: 45px;
    cursor: pointer
  }

  .menu_preview_area .sub_pre_menu_list li:hover .sub_pre_menu_inner {
    border-top-width: 0
  }

  .menu_preview_area .sub_pre_menu_list li:hover:first-child {
    line-height: 44px
  }

  .menu_preview_area .sub_pre_menu_list li.current .sub_pre_menu_inner {
    border-top-width: 0
  }

  .menu_preview_area .sub_pre_menu_list li.current a {
    color: #44b549
  }

  .mobile_menu_preview {
    position: relative;
    width: 317px;
    height: 580px;
    background: transparent url(~@/assets/img/bg_mobile_head_default.png) no-repeat 0 0;
    background-position: 0 0;
    border: 1px solid #e7e7eb
  }

  .mobile_menu_preview .mobile_hd {
    color: #fff;
    text-align: center;
    padding-top: 30px;
    font-size: 15px;
    width: auto;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    word-wrap: normal;
    margin: 0 30px
  }

  .menu_form_area {
    display: table-cell;
    vertical-align: top;
    float: none;
    width: auto;
    *display: block;
    *zoom: 1
  }

  .menu_form_area .editor_inner {
    min-height: 560px;
    padding-bottom: 20px
  }

  .pre_menu_item {
    position: relative;
    float: left;
    line-height: 38px;
    text-align: center
  }

  .pre_menu_item .icon_menu_dot {
    background: url(~@/assets/img/index.png) 0 -36px no-repeat;
    width: 7px;
    height: 7px;
    vertical-align: middle;
    display: inline-block;
    margin-right: 2px;
    margin-top: -2px;
    *margin-top: 0
  }

  .pre_menu_item a {
    display: block;
    width: auto;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    word-wrap: normal;
    color: #616161;
    text-decoration: none
  }

  .frm_tips {
    color: #8d8d8d
  }

  .frm_tips, .frm_msg {
    padding-top: 4px;
    width: 300px
  }

  .frm_msg {
    display: none;
    overflow: hidden;
    *zoom: 1
  }

  .frm_msg i {
    float: left;
    font-weight: 400;
    font-style: normal;
    margin-right: 3px
  }

  .frm_msg i {
    float: none;
    margin-right: 0
  }

  .frm_msg.success {
    color: #7fba4f
  }

  .frm_msg.fail {
    color: #e15f63
  }

  .msg_sender {
    border: 1px solid #e7e7eb
  }

  .msg_sender .tab_navs {
    border-top-width: 0
  }

  .msg_sender .tab_content {
    padding: 0
  }

  .msg_sender .tab_content .inner {
    border-width: 0
  }

  .msg_sender .tab_panel {
    border-bottom-left-radius: 3px;
    -moz-border-radius-bottomleft: 3px;
    -webkit-border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    -moz-border-radius-bottomright: 3px;
    -webkit-border-bottom-right-radius: 3px
  }

  .msg_sender.small .tab_nav a {
    padding-left: 10px;
    padding-right: 10px
  }

  .msg_sender .simple_img .title {
    display: none
  }

  .msg_sender .tab_navs_panel {
    overflow: hidden;
    *zoom: 1;
    border-bottom: 1px solid #e7e7eb
  }

  .msg_sender .tab_navs_wrp {
    overflow: hidden;
    *zoom: 1
  }

  .msg_sender .tab_navs {
    white-space: nowrap;
    text-align: left;
    font-size: 0;
    border-bottom-width: 0
  }

  .tab_navs {
    *zoom: 1;
    text-align: center;
    line-height: 30px;
    border-bottom: 1px solid #e7e7eb;
    box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.5);
    -moz-box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.5);
    -webkit-box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.5)
  }

  .tab_navs:after {
    content: "\200B";
    display: block;
    height: 0;
    clear: both
  }

  .tab_nav {
    float: left;
    font-size: 14px
  }

  .tab_nav a {
    display: block;
    *height: 1%;
    text-decoration: none;
    color: #222;
    outline: 0;
    padding: 0 20px
  }

  .msg_sender .tab_nav {
    float: none;
    display: inline-block;
    *display: inline;
    *zoom: 1;
    vertical-align: top
  }

  .msg_sender .tab_navs_switch_wrp {
    display: none;
    margin-top: 6px
  }

  .msg_sender .tab_navs_switch_wrp.switch_prev {
    float: left
  }

  .msg_sender .tab_navs_switch_wrp.switch_next {
    float: right
  }

  .msg_sender .tab_navs_switch {
    line-height: 300px;
    overflow: hidden;
    cursor: pointer
  }

  .msg_sender .switch_prev .tab_navs_switch {
    background: transparent url(~@/assets/img/icon_menu_nav_prev.png) no-repeat 0 0;
    width: 24px;
    height: 24px;
    vertical-align: middle;
    display: inline-block;
    background-position: 50% 50%
  }

  .msg_sender .switch_next .tab_navs_switch {
    background: transparent url(~@/assets/img/icon_menu_nav_next.png) no-repeat 0 0;
    width: 24px;
    height: 24px;
    vertical-align: middle;
    display: inline-block;
    background-position: 50% 50%
  }

  .tab_cont_cover {
    overflow: hidden
  }

  .tab_cont_cover .create_access {
    padding: 82px 0;
    margin-bottom: 0;
    line-height: normal;
    position: relative
  }

  .tab_cont_cover .create_access strong {
    padding-top: 2px
  }

  .tab_cont_cover .media_cover {
    width: 48%;
    *width: 47%;
    margin-right: 2%;
    float: left
  }

  .tab_cont_cover .create_access .add_gray_wrp, .tab_cont_cover .create_access_primary .add_gray_wrp {
    display: inline-block
  }

  .tab_cont_cover .appmsg_cover .create_access:hover .add_gray_wrp {
    display: none
  }

  .tab_cont_cover .media_cover .create_access:hover .add_gray {
    display: inline-block
  }

  .tab_cont_cover .media_cover .create_access a:hover {
    text-decoration: none
  }

  .portable_editor {
    position: relative
  }

  .portable_editor .editor_inner {
    padding: 0 20px 5px;
    background-color: #f4f5f9;
    border: 1px solid #e7e7eb;
    border-radius: 0;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    box-shadow: none;
    -moz-box-shadow: none;
    -webkit-box-shadow: none
  }

  .portable_editor .editor_arrow_wrp, .portable_editor .editor_arrow {
    position: absolute
  }

  .frm_label {
    float: left;
    width: 5em;
    margin-top: .3em;
    margin-right: 1em;
    font-size: 14px
  }

  .portable_editor .frm_label .title {
    font-weight: 400;
    font-style: normal
  }

  .portable_editor .frm_control_group {
    margin-top: 30px;
    margin-bottom: 30px;
    padding-bottom: 0
  }

  .portable_editor .edit_item .frm_label {
    *zoom: 1;
    display: block;
    float: none;
    width: auto;
    margin-bottom: 6px
  }

  .portable_editor .edit_item .frm_label:after {
    content: "\200B";
    display: block;
    height: 0;
    clear: both
  }

  .portable_editor .edit_item .frm_input_box {
    display: block;
    width: auto;
    border-color: #e7e7eb;
    height: auto;
    min-height: 30px
  }

  .portable_editor .edit_item.dropdown_item .frm_label {
    float: left;
    margin-top: 0;
    line-height: 32px
  }

  .portable_editor.to_left {
    padding-left: 12px
  }

  .portable_editor.to_left .editor_arrow_wrp {
    left: 0;
    top: 20px
  }

  .portable_editor.to_left .editor_arrow {
    display: inline-block;
    width: 0;
    height: 0;
    border-width: 12px;
    border-style: dashed;
    border-color: transparent;
    border-left-width: 0;
    border-right-color: #e7e7eb;
    border-right-style: solid
  }

  .portable_editor.to_left .editor_arrow_out {
    left: 0
  }

  .portable_editor.to_left .editor_arrow_in {
    left: 1px;
    border-right-color: #f4f5f9
  }

  .portable_editor.to_right .editor_arrow_wrp {
    right: 0;
    top: 20px
  }

  .portable_editor.to_right .editor_arrow {
    display: inline-block;
    width: 0;
    height: 0;
    border-width: 12px;
    border-style: dashed;
    border-color: transparent;
    border-right-width: 0;
    border-left-color: #e7e7eb;
    border-left-style: solid
  }

  .portable_editor.to_right .editor_arrow_out {
    right: 0
  }

  .portable_editor.to_right .editor_arrow_in {
    right: 1px;
    border-left-color: #f4f5f9
  }

  .portable_editor.to_top .editor_arrow_wrp {
    top: 0;
    left: 20px
  }

  .portable_editor.to_top .editor_arrow {
    display: inline-block;
    width: 0;
    height: 0;
    border-width: 12px;
    border-style: dashed;
    border-color: transparent;
    border-top-width: 0;
    border-bottom-color: #e7e7eb;
    border-bottom-style: solid
  }

  .portable_editor.to_top .editor_arrow_out {
    top: 0
  }

  .portable_editor.to_top .editor_arrow_in {
    top: 1px;
    border-bottom-color: #f4f5f9
  }

  .portable_editor.to_bottom .editor_arrow_wrp {
    bottom: 0;
    left: 20px
  }

  .portable_editor.to_bottom .editor_arrow {
    display: inline-block;
    width: 0;
    height: 0;
    border-width: 12px;
    border-style: dashed;
    border-color: transparent;
    border-bottom-width: 0;
    border-top-color: #e7e7eb;
    border-top-style: solid
  }

  .portable_editor.to_bottom .editor_arrow_out {
    bottom: 0
  }

  .menu_form_area .portable_editor.to_left .editor_arrow_wrp {
    top: 545px
  }

  .portable_editor.to_bottom .editor_arrow_in {
    bottom: 1px;
    border-top-color: #f4f5f9
  }

  .menu_content {
    padding: 16px 20px;
    border: 1px solid #e7e7eb;
    background-color: #fff
  }

  .menu_content .frm_control_group {
    margin-top: 0
  }

  .menu_content.send {
    border: 0;
    padding: 0
  }

  .menu_content_tips {
    padding-bottom: 10px
  }

  .portable_editor .frm_control_group {
    margin-bottom: 10px
  }

  .sub_pre_menu_box {
    position: absolute;
    bottom: 50px;
    left: 0;
    width: 100%;
    border: 1px solid #d0d0d0;
    background-color: #fff
  }

  .sub_pre_menu_box .arrow {
    position: absolute;
    left: 50%;
    margin-left: -6px
  }

  .sub_pre_menu_box .arrow_out {
    bottom: -6px;
    display: inline-block;
    width: 0;
    height: 0;
    border-width: 6px;
    border-style: dashed;
    border-color: transparent;
    border-bottom-width: 0;
    border-top-color: #d0d0d0;
    border-top-style: solid
  }

  .sub_pre_menu_box .arrow_in {
    bottom: -5px;
    display: inline-block;
    width: 0;
    height: 0;
    border-width: 6px;
    border-style: dashed;
    border-color: transparent;
    border-bottom-width: 0;
    border-top-color: #fafafa;
    border-top-style: solid
  }

  .frm_controls {
    display: table-cell;
    vertical-align: top;
    float: none;
    width: auto;
    *display: block;
    *zoom: 1
  }

  .frm_controls:after {
    content: " . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ";
    visibility: hidden;
    clear: both;
    height: 0 !important;
    display: block;
    line-height: 0
  }

  .frm_input {
    width: 100%;
    background-color: transparent;
    border: 0;
    outline: 0;
    height: 22px;
    *line-height: 22px;
    margin: 4px 0
  }

  .frm_input_box {
    display: inline-block;
    position: relative;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    width: 278px;
    font-size: 14px;
    padding: 0 10px;
    border: 1px solid #e7e7eb;
    box-shadow: none;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    border-radius: 0;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    background-color: #fff
  }

  .frm_input_box.append {
    padding-right: 30px
  }

  .menu_setting_area {
    *zoom: 1;
    margin: 14px 30px 0
  }

  .menu_setting_area:after {
    content: "\200B";
    display: block;
    height: 0;
    clear: both
  }

  .frm_radio {
    position: absolute;
    left: -999em
  }

  .icon_radio {
    margin-right: 4px
  }

  .tab_panel {
    background-color: #fff;
    min-height: 216px
  }

  .tab_content {
    padding: 35px 30px 0
  }

  .tab_content .inner {
    border: 1px solid #c6c6c6;
    border-radius: 3px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    padding: 14px 20px
  }

  .tab_content .inner.no_extra {
    padding: 0;
    border-width: 0
  }

  .global_mod .global_extra {
    text-align: right
  }

  .global_mod.mt_layout .global_extra {
    margin-top: -40px
  }

  .global_mod.float_layout {
    *zoom: 1
  }

  .global_mod.float_layout:after {
    content: "\200B";
    display: block;
    height: 0;
    clear: both
  }

  .global_mod.float_layout .global_info {
    float: left
  }

  .mini_tips.warn {
    color: #e15f63
  }

  .icon_msg_small {
    width: 32px;
    height: 32px;
    vertical-align: middle;
    display: inline-block
  }

  .icon_msg_small.success {
    background: url(~@/assets/img/base.png) 0 -936px no-repeat
  }

  .icon_msg_small.warn {
    background: url(~@/assets/img/base.png) 0 -972px no-repeat
  }

  .icon_msg_small.info {
    background: url(~@/assets/img/base.png) 0 -1008px no-repeat
  }

  .icon_msg_small.waiting {
    background: url(~@/assets/img/base.png) 0 -1044px no-repeat
  }

  .icon_msg_small.ask {
    background: url(~@/assets/img/base.png) 0 -1080px no-repeat
  }

  .icon20_common {
    width: 20px;
    height: 20px;
    vertical-align: middle;
    display: inline-block
  }

  .icon20_common.add_gray {
    background: url(~@/assets/img/base.png) 0 -2485px no-repeat
  }

  .icon36_common {
    width: 36px;
    height: 36px;
    vertical-align: middle;
    display: inline-block
  }

  .icon36_common.add_gray {
    background: url(~@/assets/img/base.png) 0 -2548px no-repeat
  }

  .create_access {
    display: block;
    border: 2px dotted #d9dadc;
    line-height: 80px;
    text-align: center;
    margin-bottom: 20px
  }

  .create_access i {
    cursor: pointer;
    line-height: 300px;
    overflow: hidden
  }

  .create_access a {
    display: none;
    vertical-align: middle;
    margin-left: 10px;
    margin-right: 10px;
    color: #8d8d8d;
    font-size: 14px;
    line-height: normal
  }

  .create_access a strong {
    display: block;
    padding-top: 3px;
    font-weight: 400;
    font-style: normal
  }

  .create_access:hover {
    border-color: #b3b3b3
  }

  .create_access:hover .add_gray {
    display: none
  }

  .create_access:hover a {
    display: inline-block
  }

  .menu_form_hd {
    padding: 9px 0;
    border-bottom: 1px solid #e7e7eb
  }

  .menu_form_hd h4 {
    font-weight: 400
  }
</style>
