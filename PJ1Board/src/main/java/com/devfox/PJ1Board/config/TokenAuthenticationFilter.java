package com.devfox.PJ1Board.config;

/*
 * 作成者: チェヨンジュン
 * Class名 : TokenAuthenticationFilter
 * 機能 : アクセストークンが入ったAuthorizationヘッダー値を取得した後アクセストークンが有効なときに認証情報を設定
 * Date: 2025-01-17
 * */

import com.devfox.PJ1Board.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal
            (HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain) throws ServletException, IOException {

        //リクエストヘッダ の Authorization キー値照会
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        //インポートされた値からプレフィクスを削除
        String token = getAccessToken(authorizationHeader);

        //インポートされたトークンが有効であることを確認し、有効なときは認証情報を生成
        if (tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}
