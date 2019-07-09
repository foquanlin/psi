package com.platform.modules.app.controller;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.RestResponse;
import com.platform.modules.oss.cloud.UploadFactory;
import com.platform.modules.oss.entity.SysOssEntity;
import com.platform.modules.oss.service.SysOssService;
import com.platform.modules.sys.entity.TbUserEntity;
import com.platform.modules.sys.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @author 李鹏军
 */
@RestController
@RequestMapping("/app/user")
@Api(tags = "AppUserController|APP用户接口")
public class AppUserController {

    @Autowired
    private TbUserService userService;
    @Autowired
    private SysOssService sysOssService;

    /**
     * 根据token获取用户信息
     *
     * @param user user
     * @return RestResponse
     */
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息", notes = "根据token获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "用户token", required = true, dataType = "string"),
    })
    public RestResponse userInfo(@LoginUser TbUserEntity user) {
        return RestResponse.success().put("user", user);
    }

    /**
     * 根据openId获取用户信息
     *
     * @param openId openId
     * @return RestResponse
     */
    @IgnoreAuth
    @GetMapping("getUserInfoByOpenId")
    @ApiOperation(value = "获取用户信息-openId", notes = "根据openId获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "openId", value = "openId", required = true, dataType = "string", example = "oxaA11ulr9134oBL9Xscon5at_Gc")
    })
    public RestResponse getUserInfoByOpenId(String openId) {
        return RestResponse.success().put("user", userService.selectByOpenId(openId));
    }

    /**
     * 上传文件
     *
     * @param file file
     * @return RestResponse
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件，form表单提交")
    public RestResponse upload(@RequestParam("file") MultipartFile file) {
        if (null == file || file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String url;
        try {
            url = UploadFactory.build().uploadSuffix(file.getBytes(), suffix);
        } catch (IOException e) {
            return RestResponse.error("上传文件失败");
        }

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);

        return RestResponse.success().put("url", url);
    }
}
