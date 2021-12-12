package com.tongyi.modules.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.common.utils.PageUtils;
import com.tongyi.modules.wx.entity.Article;
import com.tongyi.modules.wx.enums.ArticleTypeEnum;

import java.util.List;
import java.util.Map;

public interface ArticleService extends IService<Article> {
    /**
     * 分页查询用户数据
     * @param params 查询参数
     * @return PageUtils 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询文章详情，每次查询后增加点击次数
     *
     * @param id
     * @return
     */
    Article findById(int id);

    /**
     * 添加或编辑文章,同名文章不可重复添加
     *
     * @param article
     */

    @Override
    boolean save(Article article);

    /**
     * 按条件分页查询
     *
     * @param title
     * @param page
     * @return
     */
    IPage<Article> getArticles(String title, int page);

    /**
     * 查看目录，不返回文章详情字段
     *
     * @param articleType
     * @param category
     * @return
     */
    List<Article> selectCategory(ArticleTypeEnum articleType, String category);

    /**
     * 文章查找，不返回文章详情字段
     *
     * @param articleType
     * @param category
     * @param keywords
     * @return
     */
    List<Article> search(ArticleTypeEnum articleType, String category, String keywords);
}
