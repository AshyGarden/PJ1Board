package com.devfox.PJ1Board.service;

/*
 * 作成者: チェヨンジュン
 * Class名 : UserDetailService
 * 機能 : Spring Securityからユーザー情報を取得するインターフェイス
 * Date: 2025-01-16
 * */

import com.devfox.PJ1Board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service //Spring Securityからユーザー情報を取得するインターフェイス
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override //ユーザー名(email)でユーザーの情報を取得するメソッド
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->new IllegalArgumentException((email)));
    }
}
