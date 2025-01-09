package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : AddArticleRequestDTO
 * 機能 : 書き込のデータ伝達者
 * Date: 2025-01-09
 * */

import com.devfox.PJ1Board.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //基本生成者
@AllArgsConstructor //全てのフィールドバリューを利用する生成者
@Getter
public class AddArticleRequestDTO {

    private String title;
    private String content;

    public Article toEntity(){ //生成者を利用して客体生成
        return Article.builder() //ビルダーパタンを利用して具現
                .title(title)
                .content(content)
                .build();
    }

}
