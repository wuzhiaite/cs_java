package com.wuzhiaite.javaweb.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Book implements Serializable {
    private String bookname;
    private String author;


}
