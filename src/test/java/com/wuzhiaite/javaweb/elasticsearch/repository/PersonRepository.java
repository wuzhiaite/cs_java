package com.wuzhiaite.javaweb.elasticsearch.repository;

import com.wuzhiaite.javaweb.elasticsearch.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person,String> {

    @Query("{\"match\":{\"name\":{\"query\":\"?0\"}}}")
    Page<Person> findByName(String name, Pageable pageable);

}
