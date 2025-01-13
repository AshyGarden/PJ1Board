package com.devfox.PJ1Board.controller;

import com.devfox.PJ1Board.domain.Article;
import com.devfox.PJ1Board.dto.ArticleListViewResponseDTO;
import com.devfox.PJ1Board.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/*
 * 作成者: チェヨンジュン
 * Class名 : BlogViewController
 * 機能 : ブログビューローラー
 * Date: 2025-01-13
 * */
@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponseDTO> articles = blogService
                        .findAll()
                        .stream()
                        .map(ArticleListViewResponseDTO::new)
                        .toList();

        model.addAttribute("articles", articles);//要求したデータをアトリビュートに挿入

        return "articleList"; //リータンの中ある名前のビュー照会
    }

}
