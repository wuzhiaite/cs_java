package com.wuzhiaite.javaweb.elasticsearch;


import com.wuzhiaite.javaweb.elasticsearch.entity.Person;
import com.wuzhiaite.javaweb.elasticsearch.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


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
        Person person = Person.builder().id("77777").name("王五").age(19).build();
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
    @Autowired
    private ReactiveElasticsearchOperations reactiveElasticsearchOperations;

    @Test
    public void reactiveEs(){
        Person block = reactiveElasticsearchOperations.findById("66666", Person.class).block();
        log.info("++++++++++++++++++++++++ {}+++++++++++++++++++",block);
        Person p = Person.builder().id("987314").name("张三丰").age(12).build();
        reactiveElasticsearchOperations.save(p)
                                       .doOnNext(System.out::print)
                                        .flatMap(person -> {
                                            return reactiveElasticsearchOperations.findById("987314",Person.class);
                                        })
                                        .block();
    }

    @Autowired
    PersonRepository repository;

    @Test
    public void getPersonByRepository(){
        List<Person> zsf = repository.findByName("张三丰");
        log.info(zsf.toString());
//        Iterable<Person> all = repository.findByNameAndId("张三丰","987314");
//        all.forEach(person -> {
//            System.out.println("================================================");
//            System.out.println(person);
//            System.out.println("================================================");
//        });

    }












}
