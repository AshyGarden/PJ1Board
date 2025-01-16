package com.devfox.PJ1Board.controller;

import com.devfox.PJ1Board.domain.Article;
import com.devfox.PJ1Board.dto.ArticleListViewResponseDTO;
import com.devfox.PJ1Board.dto.ArticleViewResponseDTO;
import com.devfox.PJ1Board.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/articles/{id}") //URLの中でidバリューを利用してメソッドを起動
    public String getArticle(@PathVariable("id") Long id, Model model){
        Article article = blogService.findByID(id);
        model.addAttribute("article", new ArticleViewResponseDTO(article));

        return "article"; //リータンの中ある名前のビュー照会
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){ //idがnull = 該当IDがないため登録要請
            model.addAttribute("article",new ArticleViewResponseDTO());
        } else{ //idが存在 = 該当IDのタイトル、登録文の修正要請
            Article article = blogService.findByID(id);
            model.addAttribute("article",new ArticleViewResponseDTO(article));
        }

        return "newArticle";
    }

}
