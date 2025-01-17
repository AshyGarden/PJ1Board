package com.devfox.PJ1Board.service;

/*
 * 作成者: チェヨンジュン
 * Class名 : TokenService
 * 機能 : 受け取ったトークンでトークンの有効性検査+有効なトークンの場合、リフレッシュトークンでユーザーIdを探す
 * Date: 2025-01-17
 * */

import com.devfox.PJ1Board.config.jwt.TokenProvider;
import com.devfox.PJ1Board.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        //トークンの有効性検査に失敗した場合、例外呼び出し
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("予期せぬトークン");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findByID(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2)); //2時間有効
    }

}
