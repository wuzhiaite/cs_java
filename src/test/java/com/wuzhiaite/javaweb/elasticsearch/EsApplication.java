package com.wuzhiaite.javaweb.elasticsearch;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import com.wuzhiaite.javaweb.elasticsearch.entity.Education;
import com.wuzhiaite.javaweb.elasticsearch.entity.Person;
import com.wuzhiaite.javaweb.elasticsearch.repository.PersonRepository;
import com.wuzhiaite.javaweb.module.temp.entity.Temp;
import com.wuzhiaite.javaweb.module.temp.service.ITempService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.AggregatorFactory;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.joda.time.Period;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
    public void updateEntity(){
        Person person = Person.builder().id("66666").name("六哥ya!!!").age(19).build();
        Person p = repository.index(person);
        p= repository.findById("66666").get();
        log.info("=========================== {} ==================",p);

    }

     @Test
    public void deleteAll(){
        repository.deleteAll();
     }

    @Test
    public void getList(){
        Sort order = Sort.by(new Sort.Order(Sort.Direction.DESC, "age"));
        Iterable<Person> all = repository.findAll(order);
        for (Person person : all) {
            log.info("==============={}================",person);
        }
        System.out.println("************************************************");
        MatchAllQueryBuilder query = QueryBuilders.matchAllQuery();
        Iterable<Person> search = repository.search(query);
        for (Person person : search) {
            log.info("==============={}================",person);
        }


    }

    @Test
    public void getPageList() throws JsonProcessingException {
//        PageRequest age = PageRequest.of(1, 10, Sort.Direction.ASC, "age");
        PageRequest of = PageRequest.of(1, 10);

        MatchAllQueryBuilder query = QueryBuilders.matchAllQuery();
        Page all = repository.findAll(of);

        Page search = repository.search(query, of);
        NativeSearchQuery nq = new NativeSearchQueryBuilder().withPageable(of).build();
        Page<Person> people = operations.queryForPage(nq, Person.class);


        log.info("==============={}================", JsonMapperUtil.toString(all));
        log.info("==============={}================", JsonMapperUtil.toString(search));
        log.info("================{}===========",JsonMapperUtil.toString(people));
    }

    /**
     *
     */
    @Test
    public void getSortList(){
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("by_country").field("country")
                .subAggregation(AggregationBuilders.dateHistogram("by_year")
                        .field("dateOfBirth")
                        .subAggregation(AggregationBuilders.avg("avg_children").field("children"))
                );
        NativeSearchQuery query = new NativeSearchQueryBuilder().addAggregation(aggregationBuilder).build();
        List<Person> people = operations.queryForList(query, Person.class);
    }




    @Autowired
    private ITempService tempService;

    @Test
    public void updateMapper(){
        List<Temp> list = tempService.list();
        tempService.remove(new QueryWrapper<Temp>());
        boolean b = tempService.saveOrUpdateBatch(list);


    }





}
