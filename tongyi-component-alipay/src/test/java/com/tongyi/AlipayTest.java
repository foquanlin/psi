package com.tongyi;

import com.tongyi.alipay.config.AliPayProperties;
import com.tongyi.alipay.service.AlipayServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-10-17
 */

public class AlipayTest {

    public static void main(String[] args){
        AliPayProperties prop = new AliPayProperties();
        prop.setSignType("RSA2");
        prop.setAppId("2021002187688525");
        prop.setAppPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDh+haJeyGLp+QLx6ZEuWLGisuG06lSqC7OIzumP9FO1I3ufsxDBYUScszp+2r0pmUyniJqAWSccZ8iNAB5rtMLGIc5DVLLAwjGxESRBZo7AQ3DhMfTMk10zpYbcL8uPK1UBLibQeuZrDDQcbJD817D8A0pGf+cAOqWrwiQsIg01imBfPnwk0f1V3/qTNd2cHnKolBCucKmuz7LouiE4MzjZGXjXqxaV2fmn854QbIFJX7z+F4P1QgucAil1h+BrgRoTyU2Q/c35DNjfjnFiRWXnfvE8GjhzANsS59XACsJMhsVUcFYE31n3r856h0/s4Siqru+l+He9pll9cwofLBNAgMBAAECggEAZ1QioAS0hxZi+Vv2VDBs0RpADEuWfhw3Ir0Llm5R9/jLWYWTQwYwHgpuVoNHGc1IMsgKNmyio8J0oIAkw39EMyupEVZdSET/61ttet6T8mz97Sw27wRuVH3uN2frk2ndW7C49Q1EfMU2oOmMOPP8nh2S9vtTXPMyUWqBwR9neLikwmkxK4t3gHjwZJ+Ivm9736ZvMxKgLDJtnWllFcVwaC1tVCDf4X8VrXxb6qGjBRqsp8HYkfGO7WBICX+EAk6IvpBxOyugNnlkNXiLB+tktWIHbgcUSHtSY1JUONhYoQG/FH7Gh3n9ETdki4Q74eyOJC0kmvE8zKhYuITfzK6eQQKBgQD28snZXB07tMQZfz9yKT+Zu00RvszILyzPg9ahEIgEiuhSfzQQ38wj0TgbqgvQnZN0AMX/O7xNqW/qWg2nkwNP1bCcN1cI2WV27qj4yBQjn0A+VUjSniQ9nfhcC8VFKRp6ezCNI2Yar3ovVPMI2CY5O5rk+uAZFRbLUOPcRTkokQKBgQDqQoQclYyVS5VmdMHq5Jvey194nDBEctXuLyBgyEHwFMbCsSBL0ra9CQa+WvkM+YaQOt/hB7GPI94qfzbRs2joqCvGGvJnW+SV1fuqjqMLgZ2s5Z3p+D5W4+O3PiwDMOUP65pbUh6hx+2ZGq8JE1NOUWUj0+hgqYEsiClDuKqJ/QKBgAOky3Ool0nVPft+VCpUD3suN1HmtdDSWV/qexU/UDJ49jqZY+RH2RRCNwi5dpkqag2RIVrFv96JfHxBvefWFHtuFMAJVQBtbMDXfqjFEv7/j4wb6AoriS9bkFmTVuLRW3GpNasvdYc5RZ1DLJ7ZyZ6YkY2FYh8cn4x6chJC9zfxAoGAeMYqToJbpGz/bX8squmJ4ud/JTOBPwjlepMZYkxckmZjwuSG/hB43iTVMTa9yA6tbZas7duFk4Kue8JyhNK2d5ycVNp3twoNY6KayWgYb0o3/He1Xnb5zNpZAJQxJMQElJynTAWefVISESlMrKUmAe688ifCWnOJgWgBvkRqyBECgYEAijNNV0SDT//fe07bfLIkAlZVpxbs08dWCWArT7M1lmQuCg+t3UJIXDgyCt5xEOxOWtNBhMQdLae09swRR11OpDlwffu3sVNS9COvBTmqMjetpihogmRt2zF4u5YF86L8l7lyRVci87r9G/aD/9PNcc1+p3FSQwEZ6qzIu4NH/Qg=");
        prop.setAlipayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwhBjozKQ8KygJrMUE+2gNtDtkI9AgDPEoMdl2cEqk7FLQvCZ9tPYu0CyVOOOutphXaua1EVZbZdoVd1J4YNK9l1dnC+2Oc5XSvPSTTFaXsdXMPDyEPIcgp5E0LcwB3njCcYrWWWmF2SUGrM7tO8HtkF8zvc+Kr7C6zP3KqZOgd9ZwaExKPXFZYT3NEVAJ/S/4pNGJY7Jat5PGAZSO/YwAp9UjgsMq0z8PllV+6cfJc+PwL0eY1ytGB3BYaODrLnMKviQXcfu3KEX9TuKlLX0UyNmvWm6F5kiPFn/p2ToQV0kPEDZLB915gEDQsKdv5nat2LlL3YgNAbZGVSHbT0R0wIDAQAB");
        prop.setEncryptKey("L5snwyF60Ojej6bXA0659g==");
//        prop.setAppCertPath("classpath:app-cert.cert");
//        prop.setAlipayCertPath("classpath:alipay-cert.cert");
//        prop.setAppCertPath("D:\\workspace-webnapp\\weitong-master\\tongyi-framework\\tongyi-component-alipay\\src\\test\\resources\\app-cert.cert");
//        prop.setAlipayCertPath("D:\\workspace-webnapp\\weitong-master\\tongyi-framework\\tongyi-component-alipay\\src\\test\\resources\\alipay-cert.cert");
//        prop.setAlipayRootCertPath("");
//        prop.setAlipayRootCertPath("D:\\workspace-webnapp\\weitong-master\\tongyi-framework\\tongyi-component-alipay\\src\\test\\resources\\alipay-cert.cert");
        AlipayServiceImpl pay = new AlipayServiceImpl(prop);
        pay.faceToFace("aaa","HALL1325557879798", BigDecimal.valueOf(100),"abcd");
    }
}
