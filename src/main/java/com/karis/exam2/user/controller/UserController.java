package com.karis.exam2.user.controller;

import com.karis.exam2.user.dao.ArticleRepository;
import com.karis.exam2.user.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class UserController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("list")
    @ResponseBody
    public List<Article> showArticle() {
        return articleRepository.findAll();
    }

    @RequestMapping("detail")
    @ResponseBody
    public Article showDetail(long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.get();
    }
    @RequestMapping("doModify")
    @ResponseBody
    public Article showModify(long id, String title, String body) {
        Article article = articleRepository.findById(id).get();
        if(title != null){
            article.setTitle(title);
        }
        if(body != null){
            article.setBody(body);
        }
        articleRepository.save(article);
        return article;
    }



}
