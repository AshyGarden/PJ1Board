package com.devfox.PJ1Board.repository;

import com.devfox.PJ1Board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * 作成者: チェヨンジュン
 * Interface名 : BlogRepository
 * 機能 : データベースからデータをCRUD(生成、読み取り、アップデート、削除)できるメソッドを提供
 * Date: 2025-01-08
 * */
public interface BlogRepository extends JpaRepository<Article, Long> {
}
