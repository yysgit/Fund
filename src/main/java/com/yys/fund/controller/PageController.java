package com.yys.fund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
    @RequestMapping("/druid")
    public String druid(){
        return "redirect:/druid/index.html";
    }

    @RequestMapping("/index.html")
    public String index(){
        return "index.html";
    }
    @RequestMapping("/index2.html")
    public String index2(){
        return "index.html";
    }
}
