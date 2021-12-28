package com.hao.demo.controller;

import com.hao.demo.entity.Article;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("article")
public class ArticleController {

    @GetMapping
    public String list(Model model) {
        List<Article> articleList = Article.randomBatch(15);
        model.addAttribute("articleList", articleList);
        return "article/list";
    }

}
