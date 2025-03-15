package com.mao.springandvideo.controller;

import com.mao.springandvideo.dao.Result;
import com.mao.springandvideo.mapper.UserMapper;
import com.mao.springandvideo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mao.springandvideo.dao.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        User user1 = userMapper.selectUserByUser(user);

        if (user1 != null){
            String token = JwtUtil.generateToken(user.getUsername()); // 生成 JWT
            response.put("success", true);
            response.put("token", token);
            return Result.success(response);
        } else {
            response.put("success", false);
            response.put("message", "Login failed");
            return Result.error("login failed");
        }
    }

    @GetMapping("/getUsers")
    public Result getUsers(){
        List<User> users = userMapper.getUsers();
        System.out.println(users);
        return Result.success(users);
    }
}
