package com.devfox.PJ1Board.service;

/*
 * 作成者: チェヨンジュン
 * Class名 : RefreshTokenService
 * 機能 : 受け取ったリフレッシュトークンでトークンオブジェクトを検索してから渡す
 * Date: 2025-01-17
 * */

import com.devfox.PJ1Board.domain.RefreshToken;
import com.devfox.PJ1Board.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new IllegalArgumentException("予期せぬトークン"));
    }
}
