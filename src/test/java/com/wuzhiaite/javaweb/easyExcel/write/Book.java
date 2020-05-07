package com.wuzhiaite.javaweb.easyExcel.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Book {
    @ExcelProperty("书名")
    private String name;
    @ExcelProperty("作者")
    private String author;
    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty("出版日期")
    private Date publishDate;
    @ExcelProperty("国家")
    private String country;

}
