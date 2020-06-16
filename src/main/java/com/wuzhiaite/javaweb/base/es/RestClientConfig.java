package com.wuzhiaite.javaweb.base.es;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.net.InetSocketAddress;

/**
 * es 的rest客户端
 * @author lpf
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    /**
     * 生成rest的高级客户端
     * @return
     */
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("106.52.137.87:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }



}
