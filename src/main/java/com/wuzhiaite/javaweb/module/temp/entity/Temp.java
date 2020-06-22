package com.wuzhiaite.javaweb.module.temp.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.annotations.ApiModel;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.util.StringUtils;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-06-17
*/
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Temp对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Temp implements Serializable {

    private static final long serialVersionUID = 1L;

    private String infernoId;

    private String cifNo;

    private String clientCode;

    private String clientNo;

    private String nameCn;

    private String nameEn;

    private String aecode;

    private String aecode2;

    private String country;

    private String crsContent;

    private String tin1;

    private String tin2;

    private String jurisdiction1;

    private String jurisdiction2;

    private String citizenship;

    private String otherTaxNumber;

    private String id;

    private String certificateNo;

    protected static final Base64.Decoder decoder = Base64.getDecoder();
    public void setCrsContent(String content) throws UnsupportedEncodingException, JsonProcessingException {
        this.crsContent = content;

        if(StringUtils.isEmpty(content)) {
            this.tin1 = "";
            this.jurisdiction1 = "";
            this.tin2 = "";
            this.jurisdiction2 = "";
            return ;
        }
        byte[] decode = decoder.decode(content);
        String tempStr = new String(decode, "UTF-8");
        JsonMapper jm = new JsonMapper();
        Map<String,Object> map = jm.readValue(tempStr, Map.class);
        String jurisdiction1 = !StringUtils.isEmpty(map.get("jurisdiction1")) ? (String) map.get("jurisdiction1") : null;
        String tin1 = !StringUtils.isEmpty(map.get("tin1")) ? (String) map.get("tin1") : null;
        String jurisdiction2 = !StringUtils.isEmpty(map.get("jurisdiction2")) ? (String) map.get("jurisdiction2") : null;
        String tin2 = !StringUtils.isEmpty(map.get("tin2")) ? (String) map.get("tin2") : null;
        this.jurisdiction1 = jurisdiction1;
        this.tin1 = tin1 ;
        this.jurisdiction2 = jurisdiction2 ;
        this.tin2 = tin2 ;
    }


    public static void main(String[] args) throws  Exception{
        String str = "eyJzZXhfcHJlZml4IjoiTXIiLCJsYXN0TmFtZSI6Ilx1OTY3MyIsImZpcnN0TmFtZSI6Ilx1NWVlM1x1OGM1MCIsIm1pZGRsZU5hbWUiOiIiLCJoa2lkX3Bhc3Nwb3J0X25vIjoiIiwib3RoZXJfbm8iOiIiLCJhZGRyZXNzIjoiXHU1ZWUzXHU2NzcxXHU3NzAxXHU2ZGYxXHU1NzMzXHU1ZTAyXHU1YjlkXHU1Yjg5XHU1MzNhXHU4OTdmXHU0ZTYxXHU1OTI3XHU5MDUzXHU1ZmExXHU5Zjk5XHU1YzQ1MS0yLTNcdTY4ZGYxXHU1ZWE3RVx1NTVhZVx1NTE0MzE1MDJcdTYyM2YiLCJhZGRyZXNzX2NpdHkiOiJcdTZkZjFcdTU3MzNcdTVlMDIiLCJhZGRyZXNzX3N0YXRlIjoiXHU1ZWUzXHU2NzcxXHU3NzAxIiwiYWRkcmVzc19jb3VudHJ5IjoiQ0hOIiwiYWRkcmVzc19wb3N0Y29kZSI6IiIsImNvcnJlc3BvbmRlbmNlX2FkZHJlc3MiOiIiLCJjb3JyZXNwb25kZW5jZV9hZGRyZXNzX2NpdHkiOiIiLCJjb3JyZXNwb25kZW5jZV9hZGRyZXNzX3N0YXRlIjoiIiwiY29ycmVzcG9uZGVuY2VfYWRkcmVzc19jb3VudHJ5IjoiIiwiY29ycmVzcG9uZGVuY2VfcG9zdGNvZGUiOiIiLCJiaXJ0aGRheSI6IjE5ODYtMDItMTciLCJiaXJ0aF9jaXR5IjoiIiwiYmlydGhfc3RhdGUiOiIiLCJiaXJ0aF9jb3VudHJ5IjoiIiwianVyaXNkaWN0aW9uMSI6IkNITiIsInRpbjEiOiI0NDE1MjIxOTg2MDIxNzA3MTUiLCJuYW1lIjoiXHU5NjczXHU1ZWUzXHU4YzUwIiwiY2FwYWNpdHkiOiIifQ==";
        byte[] decode = decoder.decode(str);
        System.out.println(new String(decode,"utf-8"));

    }




}
