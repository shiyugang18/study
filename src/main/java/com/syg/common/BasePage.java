package com.syg.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class BasePage<T> {
    private Long total;
    private List<T> content;

    public static String toIds(BasePage basePage) {
        if (basePage == null || CollectionUtils.isEmpty(basePage.getContent())) {
            return null;
        }
        return toIds(basePage.getContent());
    }

    public static String toIds(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Object obj : list) {
            String json = JSONObject.toJSONString(obj);
            if (!StringUtils.isEmpty(json)) {
                JSONObject jsonObject = JSONObject.parseObject(json);
                Object id = jsonObject.get("id");
                if (id != null) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(id.toString());
                }
            }
        }
        return sb.toString();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
