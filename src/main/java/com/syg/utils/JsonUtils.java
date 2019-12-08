package com.syg.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * json工具类
 *
 */
public final class JsonUtils {

    /**
     * 克隆
     */
    @SuppressWarnings("unchecked")
    public static <T> T clone(T obj) {
        if (obj == null) {
            return null;
        }
        return (T) clone(obj, obj.getClass());
    }

    /**
     * 克隆class
     */
    public static <T> T clone(Object obj, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        String json = toJson(obj);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return toObject(json, cls);
    }

    /**
     * 克隆
     */
    public static <T> T clone(T obj, Class<T> parametrized, Class<?>... parameterClasses) {
        if (obj == null) {
            return null;
        }
        String json = toJson(obj);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return toObject(json, parametrized, parameterClasses);
    }

    public static JSONObject toJSONObject(Object obj) {
        return JSONObject.parseObject(toJson(obj));
    }

    /**
     * jons转泛型
     */
    public static <T> T toObject(String json, Class<T> cls) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return mapper.readValue(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * jons转泛型
     */
    public static <T> T toObject(String json, Class<T> parametrized, Class<?>... parameterClasses) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        JavaType javaType = mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转json
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
