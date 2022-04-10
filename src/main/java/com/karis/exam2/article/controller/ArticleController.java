package com.karis.exam2.article.controller;

import com.karis.exam2.article.dao.ArticleRepository;
import com.karis.exam2.article.domain.Article;
import com.karis.exam2.user.dao.UserRepository;
import com.karis.exam2.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("list")
    public String showList(Model model) {
        List<Article> articles = articleRepository.findAll();

        model.addAttribute("articles", articles);
        return "usr/article/list";
    }

    @RequestMapping("modify")
    public String showModify(long id, Model model) {
        Optional<Article> opArticle = articleRepository.findById(id);
        Article article = opArticle.get();

        model.addAttribute("article", article);

        return "usr/article/modify";
    }

    @RequestMapping("doModify")
    @ResponseBody
    public String doModify(long id, String title, String body) {
        Article article = articleRepository.findById(id).get();

        if (title != null) {
            article.setTitle(title);
        }

        if (body != null) {
            article.setBody(body);
        }

        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);

        return """
                <script>
                alert('%d번 게시물이 수정되었습니다.');
                location.replace('detail?id=%d');
                </script>
                """.formatted(article.getId(), article.getId());
    }

    @RequestMapping("doDelete")
    @ResponseBody
    public String doDelete(long id) {
        if (articleRepository.existsById(id) == false) {
            return "%d번 게시물은 이미 삭제되었거나 존재하지 않습니다.".formatted(id);
        }

        articleRepository.deleteById(id);
        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }

    @RequestMapping("doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if (title == null || title.trim().length() == 0) {
            return "제목을 입력해주세요.";
        }

        title = title.trim();

        if (body == null || body.trim().length() == 0) {
            return "내용을 입력해주세요.";
        }

        body = body.trim();

        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);
        User user = userRepository.findById(1L).get();
        article.setUser(user);

        articleRepository.save(article);

        return """
                <script>
                alert('%d번 게시물이 생성되었습니다.');
                location.replace('list');
                </script>
                """.formatted(article.getId());
    }

    @RequestMapping("write")
    public String showWrite() {
        return "usr/article/write";
    }

    @RequestMapping("detail")
    public String showDetail(long id, Model model) {
        Optional<Article> opArticle = articleRepository.findById(id);
        Article article = opArticle.get();

        model.addAttribute("article", article);

        return "usr/article/detail";
    }
}
