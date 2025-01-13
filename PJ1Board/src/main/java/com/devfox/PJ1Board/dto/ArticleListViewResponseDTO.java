package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : ArticleListViewResponseDTO
 * 機能 : ビューにブログデータ伝達するDTO
 * Date: 2025-01-13
 * */

import com.devfox.PJ1Board.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponseDTO {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
