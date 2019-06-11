package com.platform.modules.app.controller;

import com.platform.common.exception.BusinessException;
import com.platform.common.utils.RestResponse;
import com.platform.modules.app.annotation.IgnoreAuth;
import com.platform.modules.app.annotation.LoginUser;
import com.platform.modules.app.entity.UserEntity;
import com.platform.modules.app.service.UserService;
import com.platform.modules.sys.entity.SysOrgEntity;
import com.platform.modules.sys.service.SysOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李鹏军
 */
@RestController
@RequestMapping("/app")
@Api(tags = "AppLoginController|APP用户接口")
public class AppUserController {

    @Autowired
    SysOrgService sysOrgService;

    @Autowired
    private UserService userService;

    /**
     * 根据token获取用户信息
     *
     * @param user user
     * @return RestResponse
     */
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息", notes = "根据token获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "string")
    })
    public RestResponse userInfo(@LoginUser UserEntity user) {
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
    @IgnoreAuth
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件，form表单提交")
    public RestResponse upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }
        return RestResponse.success();
    }

    /**
     * 根据级别获取机构
     *
     * @param orgType 机构级别
     * @return RestResponse
     */
    @IgnoreAuth
    @GetMapping("queryOrg")
    @ApiOperation(value = "根据级别获取供电单位", tags = "根据级别获取供电单位，不传查询所有")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orgType", value = "机构级别", dataType = "int", example = "2")
    })
    public RestResponse queryOrg(@RequestParam(required = false) Integer orgType) {

        Map<String, Object> params = new HashMap<>(2);
        params.put("orgType", orgType);
        List<SysOrgEntity> list = sysOrgService.queryAll(params);
        return RestResponse.success().put("list", list);
    }
}
