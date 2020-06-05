package com.wuzhiaite.javaweb.base.utils;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;

/**
 * json 处理的工具类
 * @author lpf
 */
public class JsonMapperUtil {

    private static final JsonMapper jm = new JsonMapper();

    /**
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String toString(Object obj) throws JsonProcessingException {
        Assert.notNull(obj);
        return jm.writeValueAsString(obj);
    }

    /**
     *
     * @param json
     * @param t
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T parseValue(String json,Class<T> t) throws JsonProcessingException {
        Assert.notNull(json);
        return jm.readValue(json,t);
    }

    /**
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T parseValue(byte[] bytes,Class<T> clazz) throws IOException {
        Assert.notNull(bytes);
        return jm.readValue(bytes,clazz);
    }





}
