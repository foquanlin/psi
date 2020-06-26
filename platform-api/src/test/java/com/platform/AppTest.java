package com.platform;

import static org.junit.Assert.assertTrue;

import com.qcloud.cos.utils.Jackson;
import org.junit.Test;
import org.springframework.boot.json.JacksonJsonParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
