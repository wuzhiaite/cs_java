package com.wuzhiaite.javaweb.elasticsearch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName="education_index")
public class Education {
    @Id
    private String id;
    private String edcationLevel;

    @Field(type= FieldType.Date,pattern = "yyyy-MM-dd")
    private Date startTime;
    private Date endTime;
    private String schoolName;


}
