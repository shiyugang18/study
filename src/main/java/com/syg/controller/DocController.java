package com.syg.controller;

import com.syg.common.ResponseInfo;
import com.syg.domain.pojo.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebResult;

/**
 * @Author: shiyugang
 * @Date: 2019/11/17 21:21
 */
@Controller
@RequestMapping("/v1/product")
@Slf4j
@Api(value = "DocController", tags = {"restful api示例"})
public class DocController {

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody

    @ApiOperation(value = "修改指定产品", httpMethod = "PUT", produces = "application/json")

    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "产品ID", required = true, paramType = "path")})
    public ResponseInfo update(@PathVariable("id") Integer id, @ModelAttribute Product product) {
        log.debug("修改指定产品接收产品id与产品信息=>%d,{}", id, product);
        if (id == null || "".equals(id)) {
            log.debug("产品id不能为空");
            return ResponseInfo.error(id);
        }
        return ResponseInfo.success(id);
    }
}
