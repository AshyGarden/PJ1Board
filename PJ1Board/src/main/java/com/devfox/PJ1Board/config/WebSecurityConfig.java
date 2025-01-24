package com.devfox.PJ1Board.config;

/*
 * 作成者: チェヨンジュン
 * Class名 : WebSecurityConfig
 * 機能 : 認証処理
 * Date: 2025-01-16
 * */

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

//    private final UserDetailsService userDetailsService;
//
//    //Spring Security 機能無効化
//    @Bean
//    public WebSecurityCustomizer configure(){
//        return (web)->web.ignoring()
//                .requestMatchers(PathRequest.toH2Console())
//                .requestMatchers(new AntPathRequestMatcher("/static/**"));
//    }
//
//    //特定のHttpリクエストに対するウェブベースのセキュリティ構成
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .authorizeHttpRequests(auth->auth
//                        //ログインに基づくアクセスルール
//                        .requestMatchers(
//                            //ログインなしでアクセスを許可するページ（記載されたアドレスはすべてアクセス許可）
//                            new AntPathRequestMatcher("/login"),
//                            new AntPathRequestMatcher("/signup"),
//                            new AntPathRequestMatcher("/user")
//                        ).permitAll()
//                        .anyRequest().authenticated())
//                //ログイン時に処理されるルール
//                .formLogin(formLogin->formLogin
//                        .loginPage("/login") //ログインしようとする時に入るページ
//                        .defaultSuccessUrl("/articles")) //ログインに成功すると、該当するアドレスに送ってくれる
//                .logout(logout->logout
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true))
//                .csrf(AbstractHttpConfigurer::disable) //CSRF 保護を無効にする
//                .build();
//    }
//
//    @Bean //認証管理者設定
//    public AuthenticationManager authenticationManager
//            (HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
//             UserDetailsService userDetailsService){
//
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService); //ユーザー情報サービス設定
//        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return new ProviderManager(authenticationProvider);
//    }
//
//    @Bean //パスワードエンコーダとして使用する空の登録
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
