package com.devfox.PJ1Board.controller;

/*
 * 作成者: チェヨンジュン
 * Class名 : AddUserRequestDTO
 * 機能 : ユーザーapiコントローラー
 * Date: 2025-01-16
 * */

import com.devfox.PJ1Board.dto.AddUserRequestDTO;
import com.devfox.PJ1Board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("user")
    public String signup(AddUserRequestDTO requestDTO){
        userService.save(requestDTO); //会員登録メソッド呼び出し
        return "redirect:/login"; //会員登録後、ログインページに移動
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication()); //로그아웃처리
        return "redirect:/login"; //로그아웃 처리후 로그인페이지로 이동
    }

}
