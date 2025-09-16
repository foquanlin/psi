package com.tongyi;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest{

    @Test
    public void mailTest() throws MessagingException {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        senderImpl.setHost("smtp.163.com");
        senderImpl.setUsername("cxkjehr@163.com");
        senderImpl.setPassword("AZBKYEUDMNNZJRNW");
        senderImpl.setPort(25);
        senderImpl.setDefaultEncoding("UTF-8");
        senderImpl.setJavaMailProperties(props);
        MimeMessage message = senderImpl.createMimeMessage();

        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("cxkjehr@163.com");
        helper.setTo("147657060@qq.com");
        helper.setSubject("as");
        helper.setText("content + Constant.SIGNATURE_STR", true);

        senderImpl.send(message);
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(sdf.parse("2020-06-25T16:00:00.000Z"));
    }
}
