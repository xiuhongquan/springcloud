package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Menu;
import com.atguigu.springcloud.service.Menuservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableAutoConfiguration
@RestController
public class MenuController {
    @Autowired
    private Menuservice menuservice;


    @GetMapping("/getMenu")
    public List<Menu> getMenuTree(){
        return menuservice.testMenuTree();
    }
}
