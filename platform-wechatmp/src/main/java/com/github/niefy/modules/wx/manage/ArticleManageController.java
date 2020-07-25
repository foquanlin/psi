package com.github.niefy.modules.wx.manage;

import java.util.Arrays;
import java.util.Map;

import com.github.niefy.modules.wx.entity.Article;
import com.github.niefy.modules.wx.service.ArticleService;
import com.platform.common.utils.RestResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.common.utils.PageUtils;


/**
 * 文章
 *
 * @author niefy
 * @email niefy@qq.com
 * @date 2019-11-12 18:30:16
 */
@RestController
@RequestMapping("/manage/article")
public class ArticleManageController {
    @Autowired
    private ArticleService articleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:article:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = articleService.queryPage(params);

        return RestResponse.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("wx:article:info")
    public RestResponse info(@PathVariable("id") Integer id) {
        Article article = articleService.getById(id);

        return RestResponse.success().put("article", article);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("wx:article:save")
    public RestResponse save(@RequestBody Article article) {
        articleService.save(article);

        return RestResponse.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wx:article:delete")
    public RestResponse delete(@RequestBody Integer[] ids) {
        articleService.removeByIds(Arrays.asList(ids));

        return RestResponse.success();
    }

}
