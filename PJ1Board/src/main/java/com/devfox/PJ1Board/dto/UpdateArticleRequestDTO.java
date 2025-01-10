package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : UpdateArticleRequestDTO
 * 機能 : ブログアーティクル修正要請を受けるDTO
 * Date: 2025-01-10
 * */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateArticleRequestDTO {

    private String title;
    private String content;

}
