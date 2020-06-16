package com.wuzhiaite.javaweb.elasticsearch;


import com.wuzhiaite.javaweb.elasticsearch.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@EnableAspectJAutoProxy
@EnableElasticsearchRepositories
public class EsApplication {

    @Autowired
    private ElasticsearchOperations operations ;


    @Test
    public void saveEs(){
        Person person = Person.builder().id("66666").name("张三").age(18).build();
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId())
                .withObject(person)
                .build();
        String documentId = operations.index(indexQuery);
        log.info("================  saveEs {} =================",documentId);
    }

    @Test
    public void getPerson(){
        Person person = operations.queryForObject(GetQuery.getById("66666"), Person.class);
        log.info("-----------------  getPerson:{} ---------------------------",person);

    }


}
