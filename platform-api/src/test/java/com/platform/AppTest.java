package com.platform;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.platform.common.utils.Constant;
import com.qcloud.cos.utils.Jackson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.env.PropertySources;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
        assertTrue( true );
    }
}
