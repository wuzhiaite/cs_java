package com.wuzhiaite.javaweb.elasticsearch;


import com.wuzhiaite.javaweb.elasticsearch.entity.Education;
import com.wuzhiaite.javaweb.elasticsearch.entity.Person;
import com.wuzhiaite.javaweb.elasticsearch.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Person person = Person.builder().id("66666").name("六哥").age(19).build();
//        IndexCoordinates indexCoordinates = operations.getIndexCoordinatesFor(person.getClass());
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
        Iterable<Person> all = repository.findByNameAndId("张三丰",987314);
        all.forEach(person -> {
            System.out.println("================================================");
            System.out.println(person);
            System.out.println("================================================");
        });
    }


    @Test
    public void personGroup() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Education junior = Education.builder().schoolName("江河初中").edcationLevel("junior").startTime(sdf.parse("2000-09-01"))
                .endTime(sdf.parse("2003-06-30")).build();

        Education senior = Education.builder().schoolName("江河高中").edcationLevel("senior").startTime(sdf.parse("2003-09-01"))
                .endTime(sdf.parse("2006-06-30")).build();
        ArrayList<Education> list = new ArrayList<>();
        list.add(junior);
        list.add(senior);


        Person tomcat = Person.builder().id("123324").name("tomcat").age(16).education(list).build();
        Person save = repository.save(tomcat);

    }

    @Test
    public void getList(){
        Sort order = Sort.by(new Sort.Order(Sort.Direction.DESC, "name"));
        Iterable<Person> all = repository.findAll(order);
//        repository.search()

    }






}
