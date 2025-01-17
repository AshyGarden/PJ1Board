package com.devfox.PJ1Board.repository;
/*
 * 作成者: チェヨンジュン
 * Class名 : UserRepository
 * 機能 : ユーザーを利用してロジク処理
 * Date: 2025-01-16
 * */
import com.devfox.PJ1Board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
