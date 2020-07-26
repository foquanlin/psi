package com.platform.modules.wx.manage;

import com.platform.modules.wx.entity.WxAccount;
import com.platform.modules.wx.service.WxAccountService;
import com.platform.common.utils.RestResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



/**
 * 公众号账号
 *
 * @author niefy
 * @date 2020-06-17 13:56:51
 */
@RestController
@RequestMapping("/manage/wxAccount")
public class WxAccountManageController {
    @Autowired
    private WxAccountService wxAccountService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:wxaccount:list")
    public RestResponse list(){
        List<WxAccount> list = wxAccountService.list();

        return RestResponse.success().put("list", list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{appid}")
    @RequiresPermissions("wx:wxaccount:info")
    public RestResponse info(@PathVariable("id") String appid){
		WxAccount wxAccount = wxAccountService.getById(appid);

        return RestResponse.success().put("wxAccount", wxAccount);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("wx:wxaccount:save")
    public RestResponse save(@RequestBody WxAccount wxAccount){
		wxAccountService.save(wxAccount);

        return RestResponse.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wx:wxaccount:delete")
    public RestResponse delete(@RequestBody String[] appids){
		wxAccountService.removeByIds(Arrays.asList(appids));

        return RestResponse.success();
    }

}
