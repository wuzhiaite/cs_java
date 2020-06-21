package com.wuzhiaite.javaweb.elasticsearch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName="person_index")
public class Person implements Serializable {
    @Id
    private String id;
    private String name;
    @Field(fielddata=true)
    private Integer age;
    private List<Education> education;


}
