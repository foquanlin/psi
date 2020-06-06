package com.platform;

import com.platform.modules.gen.service.SysGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformAdminApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(PlatformAdminApplicationTests.class);

    @Autowired
    private SysGeneratorService generatorService;
    @Test
    public void test() throws IOException {
        byte[] data = generatorService.generatorCode(new String[]{"SYS_LOG"},"wx","com.tongyi.wechat","author");
        File file = new File("C:/temp/autocode.zip");
        if (file.exists()){
            logger.info("文件已存在:"+file.getAbsolutePath());
        }
        OutputStream writer = new FileOutputStream(file);
        writer.write(data);
        writer.flush();
        writer.close();
    }
}
