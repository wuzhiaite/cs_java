package com.wuzhiaite.javaweb.base.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * rabbit工具类
 * @author lpf
 */
public class RabbitUtil {

    private static final JSONObject jo = new JSONObject();

    /**
     * 获取消息体中的数据
     * @param message
     * @param clazz
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     */
    public static <T> T getMessageBody(Message message, Class<T> clazz) throws UnsupportedEncodingException {
        byte[] body = message.getBody();
        String str = new String(body, "UTF-8");
        JSONObject jsonObject = new JSONObject();
        T t = jsonObject.getObject(str, clazz);
        return t ;
    }





}
