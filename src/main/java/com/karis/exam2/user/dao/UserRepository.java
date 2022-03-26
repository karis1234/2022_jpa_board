package com.karis.exam2.user.dao;

import com.karis.exam2.user.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Article, Long> {
}
