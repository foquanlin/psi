package com.platform;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.qcloud.cos.utils.Jackson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.env.PropertySources;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Unit test for simple App.
 */
public class AppTest{

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
