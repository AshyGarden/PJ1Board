package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : ArticleViewResponseDTO
 * 機能 : ビューに使用するデータ伝達用DTO
 * Date: 2025-01-14
 * */

import com.devfox.PJ1Board.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponseDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponseDTO(Article article){
        this.id = article.getId();
        this.title = article.getTitle();;
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
