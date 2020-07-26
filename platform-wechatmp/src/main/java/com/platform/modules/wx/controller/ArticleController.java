package com.platform.modules.wx.controller;

import com.platform.modules.wx.entity.Article;
import com.platform.modules.wx.enums.ArticleTypeEnum;
import com.platform.modules.wx.service.ArticleService;
import com.platform.common.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * cms文章
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 查看文章详情
     *
     * @param articleId
     * @return
     */
    @GetMapping("/detail")
    public RestResponse getArticle(int articleId) {
        Article article = articleService.findById(articleId);
        return RestResponse.success(article);
    }

    /**
     * 查看目录
     *
     * @param category
     * @return
     */
    @GetMapping("/category")
    public RestResponse getQuestions(String type, String category) {
        ArticleTypeEnum articleType = ArticleTypeEnum.of(type);
        if (articleType == null) {
            return RestResponse.error("文章类型有误");
        }
        List<Article> articles = articleService.selectCategory(articleType, category);
        return RestResponse.success(articles);
    }

    /**
     * 文章搜索
     *
     * @param category
     * @param keywords
     * @return
     */
    @GetMapping("/search")
    public RestResponse getQuestions(String type,
                          @RequestParam(required = false) String category,
                          @RequestParam(required = false) String keywords) {
        ArticleTypeEnum articleType = ArticleTypeEnum.of(type);
        if (articleType == null) {
            return RestResponse.error("文章类型有误");
        }
        if (StringUtils.isEmpty(keywords)) {
            return RestResponse.error("关键词不得为空");
        }
        List<Article> articles = articleService.search(articleType, category, keywords);
        return RestResponse.success(articles);
    }


}
