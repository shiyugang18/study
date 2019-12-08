package com.syg.controller;

import com.syg.domain.pojo.SystemUser;
import io.jsonwebtoken.lang.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: shiyugang
 * @Date: 2019/12/8 14:35
 */
@RestController
@RequestMapping
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestBody SystemUser systemUser){
        Assert.notNull(systemUser.getUserName(),"username不能为空");
        Assert.notNull(systemUser.getPassword(),"password不能为空");
        UsernamePasswordToken upToken=new UsernamePasswordToken(systemUser.getUserName(),systemUser.getPassword());
        Subject subject= SecurityUtils.getSubject();
        if(subject==null){
        throw new RuntimeException("登陆异常");
        }
        try{
        subject.login(upToken);
        }catch (Exception e){
        e.printStackTrace();
        return "login";
        }
        return "index";
    }

}
