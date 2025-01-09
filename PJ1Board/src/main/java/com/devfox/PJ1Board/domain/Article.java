package com.devfox.PJ1Board.domain;

/*
* 作成者: チェヨンジュン
* Class名 : Article
* 機能 : 書き込み登録オブジェクト
* Date: 2025-01-08
* */

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity //JPAを使用してDBテーブルとマッピングするクラスは @Entityを付ける必要があります
@NoArgsConstructor(access = AccessLevel.PROTECTED) //基本生成者
public class Article {

    @Id //ここのフィールドを基本キーとして使う
    @GeneratedValue(strategy = GenerationType.IDENTITY) //基本キーを 1ずつ増加
    @Column(name="id", updatable=false)
    private long id; //データベースの中にはBIGINTと利用

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Builder //Builderパタンを利用してオブジェクト生成(可読性向上のために)
    //生成者 自動完成　ショートカットキー＝ alt+ins(INTELLIJ)
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
