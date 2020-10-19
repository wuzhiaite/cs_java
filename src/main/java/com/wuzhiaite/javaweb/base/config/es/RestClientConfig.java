package com.wuzhiaite.javaweb.base.config.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * es 的rest客户端
 * @author lpf
 */
@Configuration
@EnableElasticsearchRepositories(
        basePackages = "org.springframework.data.elasticsearch.repository"
)
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    @Value("${es.hostAndPort}")
    private String hostAndport;

    /**
     * 生成rest的高级客户端
     * @return
     */
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(hostAndport)
                .build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        return client ;
    }



}
