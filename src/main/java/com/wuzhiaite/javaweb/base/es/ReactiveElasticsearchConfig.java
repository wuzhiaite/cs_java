package com.wuzhiaite.javaweb.base.es;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.DefaultReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.HostProvider;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.WebClientProvider;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

import java.net.InetSocketAddress;

/**
 * reactiveElasticsearch 配置
 * @author lpf
 */
@Configuration
@EnableReactiveElasticsearchRepositories
public class ReactiveElasticsearchConfig extends AbstractReactiveElasticsearchConfiguration {
    /**
     * 配置要使用的客户端。
     * @return
     */
    @Override
    @Bean
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("106.52.137.87:9200")
                .build();
        return DefaultReactiveElasticsearchClient.create(clientConfiguration);
    }


    @Bean
    public ElasticsearchConverter elasticsearchConverter() {
        return new MappingElasticsearchConverter(elasticsearchMappingContext());
    }
    @Override
    @Bean
    public SimpleElasticsearchMappingContext elasticsearchMappingContext() {
        return new SimpleElasticsearchMappingContext();
    }
    @Bean
    public ReactiveElasticsearchOperations reactiveElasticsearchOperations() {
        return new ReactiveElasticsearchTemplate(reactiveElasticsearchClient(), elasticsearchConverter());
    }




}
