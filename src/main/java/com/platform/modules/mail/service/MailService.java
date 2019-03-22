/*
 * 项目名称:platform-plus
 * 类名称:MailService.java
 * 包名称:com.platform.modules.mail.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/21 11:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.mail.service;

/**
 * 邮件相关接口
 * 系统发送：使用application.yml文件中的mail配置
 *
 * @author 李鹏军
 */
public interface MailService {

    /**
     * 发送邮件(系统发送)
     *
     * @param to      收件人Email地址，多个收件人以,（英文逗号）隔开
     * @param subject 邮件主题
     * @param content 发送内容(普通文本、html内容)
     * @return
     */
    boolean sendMail(String to, String subject, String content);

    /**
     * 发送邮件
     *
     * @param fromUserId 发件人userId
     * @param to         收件人Email地址，多个收件人以,（英文逗号）隔开
     * @param subject    邮件主题
     * @param content    发送内容(普通文本、html内容)
     * @return 是否发送成功
     */
    boolean sendMail(String fromUserId, String to, String subject, String content);

    /**
     * 发送带附件的邮件(系统发送)
     *
     * @param to       收件人Email地址，多个收件人以,（英文逗号）隔开
     * @param subject  邮件主题
     * @param content  发送内容
     * @param filePath 附件路径
     * @return 是否发送成功
     */
    boolean sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送带附件的邮件
     *
     * @param fromUserId 发件人userId
     * @param to         收件人Email地址，多个收件人以,（英文逗号）隔开
     * @param subject    邮件主题
     * @param content    发送内容
     * @param filePath   附件路径
     * @return 是否发送成功
     */
    boolean sendAttachmentsMail(String fromUserId, String to, String subject, String content, String filePath);

    /**
     * 发送正文中有静态资源（图片）的邮件(系统发送)
     *
     * @param to      收件人Email地址，多个收件人以,（英文逗号）隔开
     * @param subject 邮件主题
     * @param content 发送内容
     * @param rscPath 静态资源路径
     * @param rscId   contentId
     * @return 是否发送成功
     */
    boolean sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

    /**
     * 发送正文中有静态资源（图片）的邮件
     *
     * @param fromUserId 发件人userId
     * @param to         收件人Email地址，多个收件人以,（英文逗号）隔开
     * @param subject    邮件主题
     * @param content    发送内容
     * @param rscPath    静态资源路径
     * @param rscId      contentId
     * @return 是否发送成功
     */
    boolean sendInlineResourceMail(String fromUserId, String to, String subject, String content, String rscPath, String rscId);
}
