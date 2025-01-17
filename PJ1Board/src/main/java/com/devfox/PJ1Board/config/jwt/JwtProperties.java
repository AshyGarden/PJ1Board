package com.devfox.PJ1Board.config.jwt;

/*
 * 作成者: チェヨンジュン
 * Class名 : JwtProperties
 * 機能 : Javaクラスにproperties値を持ってきて使う
 * Date: 2025-01-17
 * */

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter @Component
@ConfigurationProperties("jwt") //Javaクラスにproperties値を持ってきて使うアノテーション
public class JwtProperties {

    private String issuer;
    private String secretKey;

}
