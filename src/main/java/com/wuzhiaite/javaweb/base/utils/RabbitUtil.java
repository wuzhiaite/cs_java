package com.wuzhiaite.javaweb.base.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.util.ClassUtils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * rabbit工具类
 * @author lpf
 */
public class RabbitUtil {

    private static final String ENCODING = Charset.defaultCharset().name();
    private static final Set<String> whiteListPatterns = // NOSONAR lower case static
            new LinkedHashSet<>(Arrays.asList("java.util.*", "java.lang.*"));
    /**
     * 获取消息体中的数据
     * @param message
     * @param clazz
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     */
    public static <T> T getMessageBody(Message message, Class<T> clazz) throws JsonProcessingException {
        String body = getBodyContentAsString(message);
        return  JsonMapperUtil.parseValue(body,clazz);
    }

    /**
     *  将body中的数据转成字符串
     * @param
     * @return
     */
    @org.jetbrains.annotations.Nullable
    public static String getBodyContentAsString(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        byte[] body = message.getBody();
        if (body == null) {
            return null;
        }
        try {
            String contentType = (messageProperties != null) ? messageProperties.getContentType() : null;
            if (MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT.equals(contentType)) {
                return SerializationUtils.deserialize(new ByteArrayInputStream(body), whiteListPatterns,
                        ClassUtils.getDefaultClassLoader()).toString();
            }
            if (MessageProperties.CONTENT_TYPE_TEXT_PLAIN.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_JSON.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_JSON_ALT.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_XML.equals(contentType)) {
                return new String(body, ENCODING);
            }
        }
        catch (Exception e) {
            // ignore
        }
        return body.toString();
    }



}
