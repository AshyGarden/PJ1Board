package com.devfox.PJ1Board.controller;

/*
 * 作成者: チェヨンジュン
 * Class名 : UserViewController
 * 機能 : ユーザーューローラー
 * Date: 2025-01-17
 * */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login(){
        return "oauthLogin";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

}
