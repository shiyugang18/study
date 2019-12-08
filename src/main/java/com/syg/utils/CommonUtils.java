package com.syg.utils;

import com.syg.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class CommonUtils {

    public static final String COMMON_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String COMMON_FORMAT_DAY = "yyyy-MM-dd";

    public static String findKeyByVal(Map<String, String> map, String val) {
        String resultStr = "";
        if (CollectionUtils.isEmpty(map)) {
            return resultStr;
        }
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null) {
                if (value.equals(val)) {
                    return key;
                }
            }

        }
        return resultStr;
    }


    public static Map<String, Object> mapClone(Map<String, Object> map) {
        Set<String> keys = map.keySet();
        if (CollectionUtils.isEmpty(keys)) {
            return map;
        }
        Map<String, Object> resultMap = new HashMap<>();
        for (String key : keys) {
            Object o = map.get(key);
            resultMap.put(key, o);
        }
        return resultMap;
    }

    public static Map<String, Object> mapByMsg(String msg) {
        Map<String, Object> resultContentMap = new HashMap<>();
        resultContentMap.put("msg", msg);
        return resultContentMap;
    }


    //拼接查询条件
    public static String spliceMap(Map<String, Object> map, String... inArr) {
        if (inArr.length > 0) {
            for (String inStr : inArr) {
                String val = ValidateUtil.paramIsEmpty(inStr, map);
                if (!StringUtils.isEmpty(val)) {
                    String[] split = val.split(",");
                    if (split != null && split.length > 0) {
                        String newStr = "";
                        for (String s : split) {
                            if (newStr.length() > 0) {
                                newStr = newStr + ",";
                            }
                            newStr += "'" + s + "'";
                        }
                        map.put(inStr, newStr);
                    }

                }
            }
        }
        if (CollectionUtils.isEmpty(map)) {
            return "";
        }
        for (String key : map.keySet()) {
            Object obj = map.get(key);
            if ("pageSize".equals(key)) {
                Integer pageSize = objToInt(obj);
                map.put("pageSize", pageSize);
                continue;
            }
            if ("pageNum".equals(key)) {
                Integer pageSize = objToInt(map.get("pageSize"));
                Integer pageNum = objToInt(obj);
                map.put("pageNum", pageNum);
                continue;
            }
            if (obj instanceof String) {
                map.put(key, obj.toString());
            }
        }

        String sortStr = map.get("sort").toString();
        if (StringUtils.isEmpty(sortStr)) {
            return sortStr;
        }
        sortStr += " " + map.get("order");
        return sortStr;
    }

    public static int objToInt(Object obj) {
        try {

            return NumberUtils.parseNumber(obj.toString(), Double.class).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Integer strToInt(String val) {
        try {
            Integer ii = NumberUtils.parseNumber(val, Integer.class);
            return ii;
        } catch (Exception e) {
            log.info("类型转换出错。");
            return null;
        }

    }

    //拼接查询条件
    public static String spliceString(String stirng) {
        String returnString = "";
        if (!StringUtils.isEmpty(stirng)) {
            String[] newStrings = stirng.split(",");
            for (int i = 0; i < newStrings.length; i++) {
                if (i == newStrings.length - 1) {
                    returnString = returnString + "'" + newStrings[i] + "'";
                } else {
                    returnString = returnString + "'" + newStrings[i] + "',";
                }
            }
        }
        return returnString;
    }

    //把 下划线转换成 首字母大写形式
    public static String xhxToJavaStr(String key) {
        if (StringUtils.isEmpty(key)) {
            return key;
        }
        if (!key.contains("_")) {
            return key;
        }
        String returnStr = "";
        boolean b = false;
        for (char c : key.toCharArray()) {
            if (c == '_') {
                b = true;
                continue;
            }
            String tmpStr = c + "";
            if (b) {
                b = false;
                tmpStr = tmpStr.toUpperCase();
            }
            returnStr += tmpStr;

        }
        return returnStr;
    }

    public static void timestampToStr(Map<String, Object> map, String key, boolean isDay) {
        if (map == null) {
            return;
        }
        Object timestamp = map.get(key);
        if (timestamp instanceof Timestamp) {
            SimpleDateFormat format = new SimpleDateFormat(isDay ? COMMON_FORMAT_DAY : COMMON_FORMAT_DATETIME);
            Date dateTime = new Date(((Timestamp) timestamp).getTime());
            map.put(key, format.format(dateTime));
        }
    }

    public static void listMapSort(List<Map<String, Object>> maps, String key) {
        if (CollectionUtils.isEmpty(maps)) {
            return;
        }
        maps.sort((a, b) -> {
            String s1 = ValidateUtil.paramIsEmpty(key, a);
            String s2 = ValidateUtil.paramIsEmpty(key, b);
            return s1.compareTo(s2);
        });
    }

    public static List<Object> toList(Object value) {
        List<Object> list = new ArrayList<>();
        list.add(value);
        return list;
    }

    public static Map<String, Object> toMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    public static Map<String, Object> listToMap(List<Map<String, Object>> maps, String key) {
        if (CollectionUtils.isEmpty(maps)) {
            return null;
        }
        Map<String, Object> resultMap = new HashMap<>();
        for (Map<String, Object> map : maps) {
            Object o = map.get(key);
            if (o != null) {
                resultMap.put(o.toString(), map);
            }
        }
        return resultMap;
    }

    //不推荐用这个
    public static Map<String, Object> idList(Map<String, Object> parameterMap) {
        String id = ValidateUtil.paramIsEmpty("id", parameterMap);
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<>();
        String[] produceIdList = id.split(",");
        List<String> produceIds = new ArrayList<>();
        for (String produceId : produceIdList) {
            String str = "'" + produceId + "'";
            produceIds.add(str);
        }

        paramMap.put("idList", produceIds);
        return paramMap;
    }

    public static Map<String, Object> idListEx(Map<String, Object> parameterMap) {
        return idListEx(parameterMap, null, null);
    }

    public static Map<String, Object> idListEx(Map<String, Object> parameterMap, String key, String val) {
        String id = ValidateUtil.paramIsEmpty("id", parameterMap);
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException("4041", "参数不能为空，请选择数据");
        }
        Map<String, Object> paramMap = new HashMap<>();
        List<String> produceIds = Arrays.asList(id.split(","));
        paramMap.put("idsList", produceIds);
        if (key != null) {
            paramMap.put("key", key);
            paramMap.put("val", val);
        }
        return paramMap;
    }

    public static Map<String, Object> queryList(Map<String, Object> parameterMap, String... key) {
        for (String k : key) {
            queryOneList(parameterMap, k);
        }
        return parameterMap;
    }

    public static void queryOneList(Map<String, Object> parameterMap, String key) {
        String id = ValidateUtil.paramIsEmpty(key, parameterMap);
        if (StringUtils.isEmpty(id)) {
            return;
        }
        String[] produceIdList = id.split(",");
        List<String> produceIds = new ArrayList<>();
        for (String produceId : produceIdList) {
            produceIds.add(produceId);
        }
        parameterMap.remove(key);
        parameterMap.put(key + "List", produceIds);
    }

    public static Map<String, Object> toMap(Object value) {
        if (value == null) {
            throw new BusinessException("4561", "数据不能为空");
        }
        return toMap("id", value);
    }

    public static Map<String, Object> manageSchool(Map<String, Object> query) {
        if (query == null) {
            query = new HashMap<>();
        }
        String key = "schoolId";
        String schoolId = ValidateUtil.paramIsEmpty(key, query);
        if (StringUtils.isEmpty(schoolId)) {
            String currentSchoolId = TokenUtil.getCurrentSchoolId();
            if (!StringUtils.isEmpty(currentSchoolId)) {
                query.put(key, currentSchoolId);
            }
        }
        return query;
    }

    public static Map<String, Object> manageUserId(Map<String, Object> query) {
        if (query == null) {
            query = new HashMap<>();
        }
        String key = "userId";
        String userId = ValidateUtil.paramIsEmpty(key, query);
        if (StringUtils.isEmpty(userId)) {
            String currentUserId = TokenUtil.getCurrentUserId();
            if (!StringUtils.isEmpty(currentUserId)) {
                query.put(key, currentUserId);
            }
        }
        return query;
    }

    public static boolean intOne(Integer i) {
        if (i == null) {
            return false;
        }
        return i.equals(1);
    }

    public static boolean intZero(Integer i) {
        if (i == null) {
            return false;
        }
        return i.equals(0);
    }

    public static String yinhao(String id) {
        if (StringUtils.isEmpty(id) || id.length() < 2) {
            return id;
        }
        return id.substring(1, id.length() - 1);
    }

}
