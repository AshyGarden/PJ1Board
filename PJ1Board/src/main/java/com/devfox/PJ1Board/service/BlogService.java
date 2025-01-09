package com.devfox.PJ1Board.service;

/*
 * 作成者: チェヨンジュン
 * Class名 : BlogService
 * 機能 : 書き込のデータビジネスロジック処理
 * Date: 2025-01-09
 * */

import com.devfox.PJ1Board.domain.Article;
import com.devfox.PJ1Board.dto.AddArticleRequestDTO;
import com.devfox.PJ1Board.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //finalとか@NotNullを利用するフィールドに生成者を作る
@Service //サービスビーンアノテーション：
public class BlogService {

    private final BlogRepository blogRepository;

    //ブログ書き込追加メソッド
    public Article save(AddArticleRequestDTO requestDTO){
        return blogRepository.save(requestDTO.toEntity());
    }

    //ブログの投稿をすべて取得するメソッド
    public List<Article> findAll(){
        return  blogRepository.findAll();
    }

    //ブログの中で特定アーティクルを取得するメソッド
    public Article findByID(Long id){
        return blogRepository.findById(id) //idを見つけた場合、id値に該当するタイトル、コメントを返します
                .orElseThrow(()->new IllegalArgumentException(id + " : not Found!"));
                //idが見つからない場合は例外処理を実行
    }

}
