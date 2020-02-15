/*
 * 项目名称:platform-plus
 * 类名称:MailServiceImpl.java
 * 包名称:com.platform.modules.mail.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/21 11:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service.impl;

import com.platform.common.utils.Constant;
import com.platform.common.utils.ShiroUtils;
import com.platform.modules.sys.service.MailService;
import com.platform.modules.sys.entity.SysMailLogEntity;
import com.platform.modules.sys.entity.SysUserEntity;
import com.platform.modules.sys.service.SysMailLogService;
import com.platform.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * @author 李鹏军
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysMailLogService mailLogService;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendMail(String to, String subject, String content) {
        return sendMail(null, to, subject, content);
    }

    @Override
    public boolean sendMail(String fromUserId, String to, String subject, String content) {

        SysMailLogEntity mailLogEntity = new SysMailLogEntity();
        mailLogEntity.setReceiver(to);
        mailLogEntity.setContent(content + Constant.SIGNATURE_STR);
        mailLogEntity.setSubject(subject);
        mailLogEntity.setSendDate(new Date());
        mailLogEntity.setSendResult(0);

        boolean result = false;
        try {
            if (null != fromUserId) {
                SysUserEntity user = userService.getById(fromUserId);
                JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                senderImpl.setHost(user.getEmailHost());
                senderImpl.setUsername(user.getEmail());
                senderImpl.setPassword(user.getEmailPw());
                senderImpl.setDefaultEncoding("UTF-8");
                senderImpl.setJavaMailProperties(props);
                MimeMessage message = senderImpl.createMimeMessage();

                //true表示需要创建一个multipart message
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(user.getEmail());
                helper.setTo(to.split(","));
                helper.setSubject(subject);
                helper.setText(content + Constant.SIGNATURE_STR, true);

                senderImpl.send(message);
                log.info("邮件发送成功");

                //保存发送日志
                mailLogEntity.setCreateUserId(Constant.SUPER_ADMIN);
                mailLogEntity.setCreateUserOrgNo(Constant.SUPER_ADMIN_ORG);
                mailLogEntity.setSender(user.getEmail());
                mailLogEntity.setType(Constant.USER_SEND);
                result = true;
            } else {
                MimeMessage message = mailSender.createMimeMessage();
                //true表示需要创建一个multipart message
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(from);
                helper.setTo(to.split(","));
                helper.setSubject(subject);
                helper.setText(content + Constant.SIGNATURE_STR, true);

                mailSender.send(message);
                log.info("邮件发送成功");

                //当前登录人
                SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
                mailLogEntity.setCreateUserId(sysUserEntity.getUserId());
                mailLogEntity.setCreateUserOrgNo(sysUserEntity.getOrgNo());
                mailLogEntity.setSender(from);
                mailLogEntity.setType(Constant.SYS_SEND);
                result = true;
            }
        } catch (MessagingException e) {
            mailLogEntity.setSendResult(1);
            log.error("发送邮件时发生异常！", e);
        }
        mailLogService.add(mailLogEntity);
        return result;
    }

    @Override
    public boolean sendAttachmentsMail(String to, String subject, String content, String filePath) {
        return sendAttachmentsMail(null, to, subject, content, filePath);
    }

    @Override
    public boolean sendAttachmentsMail(String fromUserId, String to, String subject, String content, String filePath) {
        SysMailLogEntity mailLogEntity = new SysMailLogEntity();
        mailLogEntity.setReceiver(to);
        mailLogEntity.setContent(content + Constant.SIGNATURE_STR);
        mailLogEntity.setSubject(subject);
        mailLogEntity.setSendDate(new Date());
        mailLogEntity.setSendResult(0);

        boolean result = false;
        try {
            if (null != fromUserId) {
                SysUserEntity user = userService.getById(fromUserId);

                JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                senderImpl.setHost(user.getEmailHost());
                senderImpl.setUsername(user.getEmail());
                senderImpl.setPassword(user.getEmailPw());
                senderImpl.setDefaultEncoding("UTF-8");
                senderImpl.setJavaMailProperties(props);
                MimeMessage message = senderImpl.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(user.getEmail());
                helper.setTo(to.split(","));
                helper.setSubject(subject);
                helper.setText(content + Constant.SIGNATURE_STR, true);

                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                helper.addAttachment(fileName, file);

                senderImpl.send(message);

                //保存发送日志
                mailLogEntity.setCreateUserId(Constant.SUPER_ADMIN);
                mailLogEntity.setCreateUserOrgNo(Constant.SUPER_ADMIN_ORG);
                mailLogEntity.setSender(user.getEmail());
                mailLogEntity.setType(Constant.USER_SEND);
                result = true;
            } else {
                MimeMessage message = mailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(from);
                helper.setTo(to.split(","));
                helper.setSubject(subject);
                helper.setText(content + Constant.SIGNATURE_STR, true);

                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator)).replace(File.separator,"");
                helper.addAttachment(fileName, file);

                mailSender.send(message);

                //当前登录人
                SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
                mailLogEntity.setCreateUserId(sysUserEntity.getUserId());
                mailLogEntity.setCreateUserOrgNo(sysUserEntity.getOrgNo());
                mailLogEntity.setSender(from);
                mailLogEntity.setType(Constant.SYS_SEND);
                result = true;
            }
        } catch (MessagingException e) {
            mailLogEntity.setSendResult(1);
            log.error("发送带附件的邮件时发生异常！", e);
        }
        mailLogService.add(mailLogEntity);
        return result;
    }

    @Override
    public boolean sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        return sendInlineResourceMail(null, to, subject, content, rscPath, rscId);
    }

    @Override
    public boolean sendInlineResourceMail(String fromUserId, String to, String subject, String content, String rscPath, String rscId) {
        SysMailLogEntity mailLogEntity = new SysMailLogEntity();
        mailLogEntity.setReceiver(to);
        mailLogEntity.setContent(content + Constant.SIGNATURE_STR);
        mailLogEntity.setSubject(subject);
        mailLogEntity.setSendDate(new Date());
        mailLogEntity.setSendResult(0);

        boolean result = false;
        try {
            if (null != fromUserId) {
                SysUserEntity user = userService.getById(fromUserId);
                JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                senderImpl.setHost(user.getEmailHost());
                senderImpl.setUsername(user.getEmail());
                senderImpl.setPassword(user.getEmailPw());
                senderImpl.setDefaultEncoding("UTF-8");
                senderImpl.setJavaMailProperties(props);
                MimeMessage message = senderImpl.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(user.getEmail());
                helper.setTo(to.split(","));
                helper.setSubject(subject);
                helper.setText(content + Constant.SIGNATURE_STR, true);

                FileSystemResource res = new FileSystemResource(new File(rscPath));
                helper.addInline(rscId, res);

                senderImpl.send(message);
                log.info("嵌入静态资源的邮件已经发送。");

                //保存发送日志
                mailLogEntity.setCreateUserId(Constant.SUPER_ADMIN);
                mailLogEntity.setCreateUserOrgNo(Constant.SUPER_ADMIN_ORG);
                mailLogEntity.setSender(user.getEmail());
                mailLogEntity.setType(Constant.USER_SEND);
                result = true;
            } else {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(from);
                helper.setTo(to.split(","));
                helper.setSubject(subject);
                helper.setText(content + Constant.SIGNATURE_STR, true);

                FileSystemResource res = new FileSystemResource(new File(rscPath));
                helper.addInline(rscId, res);

                mailSender.send(message);
                log.info("嵌入静态资源的邮件已经发送。");

                //当前登录人
                SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
                mailLogEntity.setCreateUserId(sysUserEntity.getUserId());
                mailLogEntity.setCreateUserOrgNo(sysUserEntity.getOrgNo());
                mailLogEntity.setSender(from);
                mailLogEntity.setType(Constant.SYS_SEND);
                result = true;
            }
        } catch (MessagingException e) {
            mailLogEntity.setSendResult(1);
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
        mailLogService.add(mailLogEntity);
        return result;
    }
}
