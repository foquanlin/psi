package com.platform.config.elasticsearch;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Author Conn
 * @Date 2018/10/15
 */
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";

    @Value("${spring.es.hostName}")
    private String hostName;

    @Value("${spring.es.transport}")
    private Integer transport;

    @Value("${spring.es.cluster.name}")
    private String clusterName;

    @Value("${elasticsearch.ip}")
    String[] ipAddress;

//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        HttpHost[] hosts = Arrays.stream(ipAddress)
//                .map(this::makeHttpHost)
//                .filter(Objects::nonNull)
//                .toArray(HttpHost[]::new);
//        RestClientBuilder restClientBuilder = RestClient.builder(hosts);
//        return new RestHighLevelClient(restClientBuilder);
//    }

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(new InetSocketAddress(hostName,transport))
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    private HttpHost makeHttpHost(String s) {
        assert StringUtils.isNotEmpty(s);
        String[] address = s.split(":");
        if (address.length == ADDRESS_LENGTH) {
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            System.err.println(ip+"+"+port);
            return new HttpHost(ip, port, HTTP_SCHEME);
        } else {
            return null;
        }
    }
}

