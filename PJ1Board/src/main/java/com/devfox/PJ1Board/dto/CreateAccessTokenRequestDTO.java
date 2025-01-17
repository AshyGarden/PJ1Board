package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : CreateAccessTokenRequestDTO
 * 機能 : トークン生成要請DTO
 * Date: 2025-01-17
 * */

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAccessTokenRequestDTO {
    private String refreshToken;
}
