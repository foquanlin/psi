package test;

import freemarker.cache.HttpTemplateLoader;
import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.*;

public class TestFreeMarker {
    @Test
    public void createFileByFreemarker() throws IOException, TemplateException {
        //创建配置对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置默认生成文件编码
        configuration.setDefaultEncoding("utf-8");
        //设置模板路径
        //configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setTemplateLoader(new HttpTemplateLoader("http://www.wxngrok.com/"));
        //获取模板
        Template template = configuration.getTemplate("index.ftl",Locale.ROOT);
        //加载数据
        Map<String, Object> dataModel  =new HashMap<>();
        dataModel.put("name", "张三");
        dataModel.put("message", "hello world");

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "苹果");
        map1.put("price", 4.5);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "香蕉");
        map2.put("price", 6.3);
        list.add(map1);
        list.add(map2);

        dataModel.put("goodsList", list);
        dataModel.put("today", new Date());
        dataModel.put("number", 123456789L);
        //创建输出对象,将文件输出到D盘根目录下
        OutputStreamWriter fileWriter = new OutputStreamWriter(System.out);
        //渲染模板和数据
        template.process(dataModel, fileWriter);
        //关闭输出
        fileWriter.close();
    }
}
