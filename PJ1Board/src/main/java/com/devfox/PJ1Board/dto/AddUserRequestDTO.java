package com.devfox.PJ1Board.dto;

/*
 * 作成者: チェヨンジュン
 * Class名 : AddUserRequestDTO
 * 機能 : ユーザー情報を含むオブジェクト
 * Date: 2025-01-16
 * */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequestDTO {

    private String email;
    private String password;
}
