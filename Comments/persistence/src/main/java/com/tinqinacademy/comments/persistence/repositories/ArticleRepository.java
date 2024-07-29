package com.tinqinacademy.comments.persistence.repositories;

import com.tinqinacademy.comments.persistence.models.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
}
