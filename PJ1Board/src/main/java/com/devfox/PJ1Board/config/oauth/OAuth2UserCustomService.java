package com.devfox.PJ1Board.config.oauth;

/*
 * 作成者: チェヨンジュン
 * Class名 : OAuth2UserCustomService
 * 機能 : ユーザー情報を照会し、ユーザー情報がある場合はアップデート、ない場合はユーザーデータをユーザーテーブルに保存
 * Date: 2025-01-21
 * */

import com.devfox.PJ1Board.domain.User;
import com.devfox.PJ1Board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    //リクエストを受けてユーザー情報を含むオブジェクトを返却
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);

        return user;
    }

    //ユーザーがいればアップデート、なければユーザー生成
    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(User.builder()
                        .email(email)
                        .nickname(name)
                        .build());

        return userRepository.save(user);
    }

}
