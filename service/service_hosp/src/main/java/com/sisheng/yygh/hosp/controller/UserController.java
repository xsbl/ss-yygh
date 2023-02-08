package com.sisheng.yygh.hosp.controller;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.model.acl.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
@CrossOrigin
public class UserController {

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Map<String,String> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    @GetMapping("/info")
    public Result info(String token){

        String[] arr = new String[]{"admin"};
        Map<String,Object> map = new HashMap<>();
        map.put("roles",arr);
        map.put("introduction","I am administrator 斯盛.");
        map.put("avatar","https://img2.baidu.com/it/u=2212383468,857153027&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
        map.put("name","Admin 斯盛");
        return Result.ok(map);
    }

}
