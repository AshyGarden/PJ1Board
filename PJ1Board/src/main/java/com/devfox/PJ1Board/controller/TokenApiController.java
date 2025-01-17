package com.devfox.PJ1Board.controller;

/*
 * 作成者: チェヨンジュン
 * Class名 : AddUserRequestDTO
 * 機能 : ユーザーapiコントローラー
 * Date: 2025-01-17
 * */

import com.devfox.PJ1Board.dto.CreateAccessTokenRequestDTO;
import com.devfox.PJ1Board.dto.CreateAccessTokenResponseDTO;
import com.devfox.PJ1Board.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token") //リクエストが来たらリフレッシュトークンをベースに新しいアクセストークンを作成
    public ResponseEntity<CreateAccessTokenResponseDTO> createNewAccessToken(@RequestBody CreateAccessTokenRequestDTO request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseDTO(newAccessToken));
    }

}
