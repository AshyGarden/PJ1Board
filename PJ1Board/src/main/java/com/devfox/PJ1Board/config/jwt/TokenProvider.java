package com.devfox.PJ1Board.config.jwt;

/*
 * 作成者: チェヨンジュン
 * Class名 : TokenProvider
 * 機能 : トークン作成+トークンの有効性検査+トークンから必要な情報を取得
 * Date: 2025-01-17
 * */

import com.devfox.PJ1Board.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    //JwtToken 生成メソッド
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //ヘッダータイプ: jwt
                //内容 iss: jun940317@naver.com(propertiesで設定したメール)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)               //内容 iat : 現在時間
                .setExpiration(expiry)          //内容 exp: expiry メンバー変数値
                .setSubject(user.getEmail())    //内容 sub : ユーザーメール
                .claim("id", user.getId())    //クレームid : ユーザーID
                //署名方式:秘密値とともにハッシュ値をHS256方式で暗号化
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    //トークンの有効性検証メソッド
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) //秘密値で暗号化
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) { //復号化の過程でエラー時 = 無効なトークン
            return false;
        }
    }

    //トークンベースで認証情報を取得するメソッド
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    //トークンベースでユーザーIdを取得するメソッド
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    //クレーム照会メソッド
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}