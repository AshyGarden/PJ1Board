package com.devfox.PJ1Board.service;

/*
 * 作成者: チェヨンジュン
 * Class名 : UserService
* 機能 : ユーザーサービス
 * Date: 2025-01-16
 * */

import com.devfox.PJ1Board.domain.User;
import com.devfox.PJ1Board.dto.AddUserRequestDTO;
import com.devfox.PJ1Board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public Long save(AddUserRequestDTO dto){
        return userRepository.save(User.builder() //ユーザーデータビルド
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) //패스워드 암호화
                .build()).getId();
    }

    public User findByID(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("予期せぬユーザー"));
    }
}
