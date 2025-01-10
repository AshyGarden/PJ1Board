package com.devfox.PJ1Board.controller;

/*
 * 作成者: チェヨンジュン
 * Class名 : BlogController
 * 機能 : ブログコントローラー
 * Date: 2025-01-09
 * */

import com.devfox.PJ1Board.domain.Article;
import com.devfox.PJ1Board.dto.AddArticleRequestDTO;
import com.devfox.PJ1Board.dto.ArticleResponseDTO;
import com.devfox.PJ1Board.dto.UpdateArticleRequestDTO;
import com.devfox.PJ1Board.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController //ResponseBodyに客体データをJSON方式に変換するコントローラー
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequestDTO requestDTO){
        Article saveArticle = blogService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    @GetMapping("/api/articles")
    public  ResponseEntity<List<ArticleResponseDTO>> findAllArticles(){

        List<ArticleResponseDTO> articles
                = blogService.findAll() //全データインポート
                .stream() //データ処理フロー生成
                .map(ArticleResponseDTO::new) //データを目的の形に変換
                .toList(); //結果をリストに収集

        return ResponseEntity.ok().body(articles); //要求したデータを伝送
    }

    @GetMapping("/api/articles/{id}")
    //URL経路の中でバリュー抽出
    public  ResponseEntity<ArticleResponseDTO> findArticle(@PathVariable("id") Long id){
        Article article = blogService.findByID(id);//特定アーティクル抽出
        return ResponseEntity.ok().body(new ArticleResponseDTO(article)); //要求したデータを伝送
    }

    @PutMapping("/api/articles/{id}") //修正要請 = PUT
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id,
                                                 @RequestBody UpdateArticleRequestDTO requestDTO){

        Article updateArticle = blogService.update(id, requestDTO);
        return ResponseEntity.ok().body(updateArticle);

    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id){
        blogService.delete(id); //アーティクル削除命令

        return ResponseEntity.ok().build(); //返す値がないのでそのままビルド
    }

}
