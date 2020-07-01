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
        String str = "eyJpcCI6IjEyMS4zMy42MC4xMjIiLCJpcFpvbmUiOiJcdTVlN2ZcdTRlMWMgXHU1ZTdmXHU1ZGRlIiwidXNlckFnZW50IjoiTW96aWxsYVwvNS4wIChMaW51eDsgQW5kcm9pZCA5OyBNSSA5IFRyYW5zcGFyZW50IEVkaXRpb24gQnVpbGRcL1BLUTEuMTgxMTIxLjAwMTsgd3YpIEFwcGxlV2ViS2l0XC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBWZXJzaW9uXC80LjAgQ2hyb21lXC83NC4wLjM3MjkuMTM2IE1vYmlsZSBTYWZhcmlcLzUzNy4zNiB6eWFwcFwvMi4xLjUgKFhpYW9taSBNSSA5IFRyYW5zcGFyZW50IEVkaXRpb247IEFuZHJvaWQgOSkgdXVpZFwvY2I5NzUxOTUgY2hhbm5lbFwvZ3VhbndhbmcgcmVkX2dyZWVuX3NldHRpbmdcL3JlZCBsYW5ndWFnZVwvemgtQ04iLCJ2ZXJpZnl3YXkiOiJcdTgxZWFcdTUyYTlcdTUyOWVcdTc0MDYiLCJxMSI6IjQiLCJxMiI6IjQiLCJxMyI6IjMiLCJxNCI6IjQiLCJxNSI6IjMiLCJxNiI6IjUiLCJxNyI6IjUiLCJxOCI6IjIiLCJxOSI6W1swLDAsMF0sWzAsMCwwXSxbMCwwLDBdLFsxLDMsMl0sWzAsMCwwXSxbMCwwLDBdLFswLDAsMF0sWzAsMCwwXSxbMCwwLDBdLFsxLDMsMl0sWzAsMCwwXSxbMCwwLDBdLFswLDAsMF1dLCJzY29yZSI6IjM3IiwicmVhc29uIjoiXHU3ODZlXHU4YmE0XHVmZjBjXHU1NmUwXHU1ZTAyXHU1NzNhXHU3M2FmXHU1ODgzXHU1NDhjXHU2NzJjXHU0ZWJhXHU4YmE0XHU3N2U1XHU2NmY0XHU2NWIwIn0=";
        byte[] decode = decoder.decode(str);
        System.out.println(new String(decode));

    }




}
