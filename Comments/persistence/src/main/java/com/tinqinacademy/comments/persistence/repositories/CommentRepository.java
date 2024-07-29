package com.tinqinacademy.comments.persistence.repositories;

import com.tinqinacademy.comments.api.models.output.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
