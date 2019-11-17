package com.syg.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: shiyugang
 * @Date: 2019/11/17 21:21
 */
@ApiModel(value = "产品信息")
public class Product {

    @ApiModelProperty(required = true, name = "name", value = "产品名称", dataType = "query")
    private String name;
    @ApiModelProperty(name = "type", value = "产品类型", dataType = "query")
    private String type;
}
