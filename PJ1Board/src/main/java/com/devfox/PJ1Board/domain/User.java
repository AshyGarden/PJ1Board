package com.devfox.PJ1Board.domain;

/*
 * 作成者: チェヨンジュン
 * Class名 : User
 * 機能 : ユーザー客体
 * Date: 2025-01-16
 * */

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails { //UserDetailを継承して認証オブジェクトとして使用

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Builder
    public User(String email, String password, String auth){
        this.email = email;
        this.password = password;
    }

    @Override //権限 返還
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override //user id値の返還
    public String getUsername() {
        return email;
    }

    @Override //user password値返還
    public String getPassword() {
        return password;
    }

    @Override //アカウントの有効期限を返還
    public boolean isAccountNonExpired() {
        //満了を確認するロジック(作成予定)
        return true; //true = 有効, false = 無効
    }

    @Override //アカウントロック可否を返却
    public boolean isAccountNonLocked() {
        //アカウントロック可否
        return true; //true = ロック無し, false = ロックする
    }

    @Override //パスワードの有効期限を返す
    public boolean isCredentialsNonExpired() {
        //パスワードの有効期限
        return true; //true = 満了無し, false = 満了する
    }

    @Override //アカウントの使用可否を返却
    public boolean isEnabled() {
        //アカウントの使用可否
        return true; //true = 使用可能
    }
}
