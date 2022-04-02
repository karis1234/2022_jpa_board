package com.karis.exam2.user.controller;

import com.karis.exam2.user.dao.ArticleRepository;
import com.karis.exam2.user.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
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
    public Article doModify(long id, String title, String body) {
        Article article = articleRepository.findById(id).get();
        if(title != null){
            article.setTitle(title);
        }
        if(body != null){
            article.setBody(body);
        }
        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);
        return article;
    }
    @RequestMapping("doDelete")
    @ResponseBody
    public String showDelete(long id) {
        if(articleRepository.existsById(id) == false){
            return "%d번 게시물은 없습니다.".formatted(id);
        }
        articleRepository.deleteById(id);
        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }
    @RequestMapping("writer")
    @ResponseBody
    public String writer(long id,String title, String body) {
        Article article = articleRepository.findById(id).get();
        if(title == null){
            return "%d번 게시물 제목이 없습니다.".formatted(id);
        }
        if(body == null){
            return "%d번 게시물 내용이 없습니다.".formatted(id);
        }
        if(title != null){
            article.setTitle(title);
        }
        if(body != null){
            article.setBody(body);
        }
        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);
        return "%d번 게시물이 추가되었습니다.".formatted(id);
    }


}
