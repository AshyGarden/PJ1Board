package com.devfox.PJ1Board.repository;

/*
 * 作成者: チェヨンジュン
 * Class名 : RefreshTokenRepository
 * 機能 : リフレッシュトークンリポジトリ
 * Date: 2025-01-17
 * */

import com.devfox.PJ1Board.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
