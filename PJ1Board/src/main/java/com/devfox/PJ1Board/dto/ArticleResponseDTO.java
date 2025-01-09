package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : ArticleResponseDTO
 * 機能 : ブログ中のデータ応答(レスポンス)
 * Date: 2025-01-09
 * */

import com.devfox.PJ1Board.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDTO {

    private final String title;
    private final String content;

    //ブログ中のデータをここでセット
    public ArticleResponseDTO(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
