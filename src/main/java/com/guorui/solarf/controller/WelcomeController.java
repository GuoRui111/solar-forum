package com.guorui.solarf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: GuoRUi
 * @Date: 2019/10/20 下午4:52
 * @Version 1.0
 */
@Controller
public class WelcomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
