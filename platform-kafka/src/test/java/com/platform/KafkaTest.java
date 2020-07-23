package com.platform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest()
@SpringBootApplication()
@ActiveProfiles("test")
public class KafkaTest {

    @Test
    public void test(){

    }
}
