package com.mao.springandvideo.controller;

import com.mao.springandvideo.dao.Navigator;
import com.mao.springandvideo.dao.Result;
import com.mao.springandvideo.mapper.NavigatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NavigatorController {
    @Autowired
    private NavigatorMapper navigatorMapper;

    @GetMapping("/getAllNavigators")

    public Result getAllNavigators() {
        System.out.println("Navigator----------");
        List<Navigator> navigators = navigatorMapper.getAllNavigators();
        return Result.success(navigators);
    }

    @PostMapping("/save-icon")
    public Result<String> addNavigator(@RequestBody Navigator navigator){
        System.out.println(navigator);
        navigatorMapper.addNavigator(navigator);
        return Result.success("添加成功");
    }
}
