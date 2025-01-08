package com.devfox.PJ1Board.repository;

import com.devfox.PJ1Board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
