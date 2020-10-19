package com.wuzhiaite.javaweb.base.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 导出数据的配置类
 * @author lpf
 */
@ToString
@Builder
@Data
@Accessors
public class DownloadEntity<T> {
    /**
     * 实体类字节
     */
    private Class<T> clazz ;
    /**
     * 请求
     */
    private HttpServletResponse response ;
    /**
     * 文件名称
     */
    private String fileName ;
    /**
     * 需要展示的数据
     */
    private List<T> list;
    /**
     * sheet名称
     */
    private String sheetName;
    /**
     * 动态头信息
     */
    private List<List<String>> head ;




}
