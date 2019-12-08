package com.syg.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Type;
import java.util.Map;

@Slf4j
public class BeanUtil {

    public static <T> T copyBean(Object source, Class<T> cls) {
        if (source == null) {
            return null;
        }
        T obj;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            log.info("copyBean err: {}", e.toString());
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

//    public static <T> T copyBeanNotNull(Object source, T target) {
//        if (source == null) {
//            return null;
//        }
//        String json = JSONObject.toJSONString(source);
//        JSONObject jsonObject = JSONObject.parseObject(json);
//
//        String json2 = JSONObject.toJSONString(target);
//        JSONObject jsonObject2 = JSONObject.parseObject(json2);
//
//
//        Map<String, Object> innerMap = jsonObject.getInnerMap();
//        for (String key : innerMap.keySet()) {
//            Object o = innerMap.get(key);
//            if (o != null) {
//                jsonObject2.put(key, o);
//            }
//        }
//        return jsonObject2.toJavaObject((Type) target.getClass());
//    }
}
