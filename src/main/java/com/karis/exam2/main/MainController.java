package com.karis.exam2.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String showMain() {
        return "redirect:/usr/article/list";
    }
}