package com.fasecampus.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
    /articles
    /articles/{id}
    /articles/search
    /articles/search-hashing
 */
@Controller
@RequestMapping(value = "/articles")
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map) {
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String articles(@PathVariable Long articleId, ModelMap map) {
        map.addAttribute("article", articleId); // 데이터 수정 필요.
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }

}
