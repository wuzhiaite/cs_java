package com.wuzhiaite.javaweb.elasticsearch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

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
    private Integer age;


}
