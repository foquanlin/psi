package com.platform;

import com.platform.modules.gen.service.SysGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication()
public class CodegenTests {

    private static Logger logger = LoggerFactory.getLogger(CodegenTests.class);

    @Autowired
    private SysGeneratorService generatorService;
    @Test
    public void generatorCode() throws IOException {
        String[] tables = new String[]{"VIS_USER","VIS_QUESTION"};
        String project = "wx";
        String pkgName = "com.platform.modules";
        String author = "林佛权";
        byte[] data = generatorService.generatorCode(tables,project,pkgName,author);
        String date = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
        File file = new File("C:/temp/"+date+".zip");
        if (file.exists()){
            logger.info("文件已存在:"+file.getAbsolutePath());
        }
        OutputStream writer = new FileOutputStream(file);
        writer.write(data);
        writer.flush();
        writer.close();
    }
}
