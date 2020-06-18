package com.wuzhiaite.javaweb.elasticsearch.repository;

import com.wuzhiaite.javaweb.elasticsearch.entity.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 *
 */
public interface PersonRepository extends ElasticsearchRepository<Person,String> {

    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    List<Person> findByName(String name);

    List<Person> findByNameAndId(String name, int id);


}
