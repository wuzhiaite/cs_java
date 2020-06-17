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

    private String chinaCertificateNo;

    private String hongkongCertificateNo;

    private String passortCertificateNo;

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
        String str = "eyJqdXJpc2RpY3Rpb24xIjoiQ0hOIiwidGluMSI6IjQyMTIyMzE5OTAwNDE1MjU2OSIsInJlYXNvbjEiOiIiLCJleHBsYWluMSI6IiIsIm5hbWUiOiJcdTllYzRcdTYwMWQgSHVhbmcgU2kiLCJmaXJzdE5hbWUiOiJcdTllYzRcdTYwMWQgSHVhbmcgU2kiLCJzZXhfcHJlZml4IjoiTWlzcyIsImFkZHJlc3MiOiJcdTZiNjZcdTZjNDlcdTVlMDJcdTZjNDlcdTk2MzNcdTUzM2FcdTZjNDlcdTZiMjNcdTgyZDExXHU1M2Y3XHU5NjQ0MVx1NTNmNyIsImJpcnRoZGF5IjoiMTk5MC0wNC0xNSJ9";
        byte[] decode = decoder.decode(str);
        System.out.println(new String(decode,"utf-8"));

    }




}
