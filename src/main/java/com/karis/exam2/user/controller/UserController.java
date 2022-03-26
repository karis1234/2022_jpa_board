package com.karis.exam2.user.controller;

import com.karis.exam2.user.dao.UserRepository;
import com.karis.exam2.user.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/usr/article")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("list")
    @ResponseBody
    public List<Article> users() {
        return userRepository.findAll();
    }

    @RequestMapping("1")
    @ResponseBody
    public Article user() {
        Article user = new Article();
        return user;
    }


}
