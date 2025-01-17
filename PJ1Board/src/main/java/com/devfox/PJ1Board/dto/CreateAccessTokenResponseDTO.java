package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : CreateAccessTokenRequestDTO
 * 機能 : トークン応答DTO
 * Date: 2025-01-17
 * */

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAccessTokenResponseDTO {
    private String accessToken;
}
