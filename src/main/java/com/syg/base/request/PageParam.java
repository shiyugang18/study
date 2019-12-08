package com.syg.base.request;


import lombok.Data;

import java.util.Map;


/**
 * 分页参数
 *
 */
@Data
public class PageParam {

    //当前页码
    private int pageSize;

    // 每页数，最大100
    private int pageNum;

    private PageOrderType order;

    private String sort;

    private Map<String,Object> query;

    public enum PageOrderType {

        /**
         * 升序
         */
        ASC,

        /**
         * 降序
         */
        DESC
    }
}
