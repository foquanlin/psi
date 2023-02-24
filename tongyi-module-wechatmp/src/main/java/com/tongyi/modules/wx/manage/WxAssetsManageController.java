package com.tongyi.modules.wx.manage;

import com.tongyi.modules.wx.form.MaterialFileDeleteForm;
import com.tongyi.modules.wx.service.WxAssetsService;
import com.tongyi.common.utils.RestResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 微信公众号素材管理
 * 参考官方文档：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html
 * 参考WxJava开发文档：https://github.com/Wechat-Group/WxJava/wiki/MP_永久素材管理
 */
@RestController
@RequestMapping("/manage/wxAssets")
public class WxAssetsManageController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WxAssetsService wxAssetsService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 获取素材总数
     *
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/materialCount")
    public RestResponse materialCount(@CookieValue String appid) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        WxMpMaterialCountResult res = wxAssetsService.materialCount();
        return RestResponse.success(res);
    }

    /**
     * 获取素材总数
     *
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/materialNewsInfo")
    public RestResponse materialNewsInfo(@CookieValue String appid,String mediaId) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        WxMpMaterialNews res = wxAssetsService.materialNewsInfo(mediaId);
        return RestResponse.success(res);
    }


    /**
     * 根据类别分页获取非图文素材列表
     *
     * @param type
     * @param page
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/materialFileBatchGet")
    @RequiresPermissions("wx:wxassets:list")
    public RestResponse materialFileBatchGet(@CookieValue String appid,@RequestParam(defaultValue = "image") String type,
                                  @RequestParam(defaultValue = "1") int page) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        WxMpMaterialFileBatchGetResult res = wxAssetsService.materialFileBatchGet(type,page);
        return RestResponse.success(res);
    }

    /**
     * 分页获取图文素材列表
     *
     * @param page
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/materialNewsBatchGet")
    @RequiresPermissions("wx:wxassets:list")
    public RestResponse materialNewsBatchGet(@CookieValue String appid,@RequestParam(defaultValue = "1") int page) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        WxMpMaterialNewsBatchGetResult res = wxAssetsService.materialNewsBatchGet(page);
        return RestResponse.success(res);
    }

    /**
     * 添加图文永久素材
     *
     * @param articles
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/materialNewsUpload")
    @RequiresPermissions("wx:wxassets:save")
    public RestResponse materialNewsUpload(@CookieValue String appid,@RequestBody List<WxMpNewsArticle> articles) throws WxErrorException {
        if(articles.isEmpty()) {
            return RestResponse.error("图文列表不得为空");
        }
        wxMpService.switchoverTo(appid);
        WxMpMaterialUploadResult res = wxAssetsService.materialNewsUpload(articles);
        return RestResponse.success(res);
    }

    /**
     * 修改图文素材文章
     *
     * @param form
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/materialArticleUpdate")
    @RequiresPermissions("wx:wxassets:save")
    public RestResponse materialArticleUpdate(@CookieValue String appid,@RequestBody WxMpMaterialArticleUpdate form) throws WxErrorException {
        if(form.getArticles()==null) {
            return RestResponse.error("文章不得为空");
        }
        wxMpService.switchoverTo(appid);
        wxAssetsService.materialArticleUpdate(form);
        return RestResponse.success();
    }

    /**
     * 添加多媒体永久素材
     *
     * @param file
     * @param fileName
     * @param mediaType
     * @return
     * @throws WxErrorException
     * @throws IOException
     */
    @PostMapping(value = "/materialFileUpload")//,headers = "Content-Type:multipart/form-data"
    @RequiresPermissions("wx:wxassets:save")
    public RestResponse materialFileUpload(@CookieValue String appid,
                                           @RequestParam("file")MultipartFile file,
                                           @RequestParam("fileName")String fileName,
                                           @RequestParam("mediaType")String mediaType) throws WxErrorException, IOException {
        if (file == null) {
            return RestResponse.error("文件不得为空");
        }
        wxMpService.switchoverTo(appid);
        WxMpMaterialUploadResult res = wxAssetsService.materialFileUpload(mediaType,fileName,file);
        return RestResponse.success(res);
    }

    /**
     * 删除素材
     *
     * @param form
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/materialDelete")
    @RequiresPermissions("wx:wxassets:delete")
    public RestResponse materialDelete(@CookieValue String appid,@RequestBody MaterialFileDeleteForm form) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        boolean res = wxAssetsService.materialDelete(form.getMediaId());
        return RestResponse.success(res);
    }

}
