package com.syg.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by zhoujiayuan on 2018/6/4.
 * 验证传入参数是否为空的工具类
 */
public class ValidateUtil {

    public static String paramIsEmpty(String key, Map<String, Object> map) {
        if (map != null) {
            String newString = map.get(key) == null ? "" : map.get(key).toString().trim();
            return newString;
        } else {
            return "";
        }
    }

    public static boolean paramExist(String key, Map<String, Object> map) {
        if (map == null) {
            return false;
        }
        return map.get(key) != null;
    }

    public static String validateParamContainKey(String key, Map<String, Object> map) {
        if (map != null) {
            if (map.containsKey(key) && map.get(key) != null && StringUtils.isNotEmpty(map.get(key).toString())) {
                return map.get(key).toString();
            } else {
                throw new RuntimeException("param " + key + " is null or empty");
            }
        } else {
            throw new RuntimeException("param " + key + " is null");
        }
    }

    public static Integer paramByInt(String key, Map<String, Object> map) {
        String str = paramIsEmpty(key, map);
        try {
            if (StringUtils.isNotBlank(str)) {
                return Integer.parseInt(str.trim());
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public static Double paramByDouble(String key, Map<String, Object> map) {
        String str = paramIsEmpty(key, map);
        try {
            if (StringUtils.isNotBlank(str)) {
                return Double.parseDouble(str.trim());
            }
        } catch (Exception e) {

        }
        return 0.0;
    }
}
