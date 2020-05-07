package com.wuzhiaite.javaweb.common.pagelistconfig.easyexcel.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 文件下载通用处理类
 * @author lpf
 * @since 20200507
 */
@Component
public class DownloadExcel {


    /**
     * 文件直接下载
     * @param downloadEntity
     * @throws IOException
     */
    public static void download(DownloadEntity downloadEntity) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        HttpServletResponse response = downloadEntity.getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(downloadEntity.getFileName(), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), downloadEntity.getClazz())
                .head(downloadEntity.getHead())
                .sheet(downloadEntity.getSheetName())
                .doWrite(downloadEntity.getList());
    }

    /**
     * 文件下载，报错json返回
     * @param downloadEntity
     * @throws IOException
     */
    public static void downloadFailedUsingJson(DownloadEntity downloadEntity) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        HttpServletResponse response = downloadEntity.getResponse();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode(downloadEntity.getFileName(), "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(),downloadEntity.getClazz())
                     .autoCloseStream(Boolean.FALSE)
                     .head(downloadEntity.getHead())
                     .sheet(downloadEntity.getSheetName())
                     .doWrite(downloadEntity.getList());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }





}
