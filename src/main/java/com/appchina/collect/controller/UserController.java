package com.appchina.collect.controller;

import com.appchina.collect.domain.User;
import com.appchina.collect.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liujianmeng on 2017/8/14.
 */
@RestController
public class UserController {
    @Resource
    private UserDaoService userDaoService;
    @RequestMapping(value = "/user/man",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonResult<List<User>> getUserByUserCode(){
        List<User> list = userDaoService.getUserByUserCode(1);
        return new JsonResult<List<User>>(true,"OK",list);
    }


    @RequestMapping("/index")
    public String index(){
        return "index.htm";
    }


}
