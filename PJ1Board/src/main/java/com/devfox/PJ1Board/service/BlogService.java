package com.devfox.PJ1Board.service;

/*
 * 作成者: チェヨンジュン
 * Class名 : BlogService
 * 機能 : 書き込のデータビジネスロジック処理
 * Date: 2025-01-09
 * */

import com.devfox.PJ1Board.domain.Article;
import com.devfox.PJ1Board.dto.AddArticleRequestDTO;
import com.devfox.PJ1Board.dto.UpdateArticleRequestDTO;
import com.devfox.PJ1Board.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //finalとか@NotNullを利用するフィールドに生成者を作る
@Service //サービスビーンアノテーション：
public class BlogService {

    private final BlogRepository blogRepository;

    //ブログ書き込追加メソッド
    public Article save(AddArticleRequestDTO requestDTO, String userName){
        return blogRepository.save(requestDTO.toEntity(userName));
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

    @Transactional //相互作用の単位、これ以上割れない最小の演算
    public Article update(long id, UpdateArticleRequestDTO requestDTO){

        //該当するアーティクルを探すロジック
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(id + " : not Found!"));

        authorizeArticleAuthor(article);
        //アーティクルアップデート
        article.update(requestDTO.getTitle(), requestDTO.getContent());

        return article;
    }

    //アーティクル削除
    public void delete(long id){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("見つかりません: " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("無許可ユーザー");
        }
    }

}
