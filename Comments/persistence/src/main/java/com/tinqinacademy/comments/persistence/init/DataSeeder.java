package com.tinqinacademy.comments.persistence.init;


import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@Order(1)
public class DataSeeder implements ApplicationRunner {
    private final CommentRepository commentRepository;


    @Autowired
    public DataSeeder(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedComments();
    }

    private void seedComments() {
        if (commentRepository.count() != 0) {
            log.info("CommentDataSeeder - didn't seed any comments.");
            return;
        }

        Comment comment1 = Comment.builder()
                .userId(UUID.randomUUID())
                .content("This is a sample comment.")
                .roomId(UUID.randomUUID())
                .lastEditedBy(UUID.randomUUID())
                .build();

        Comment comment2 = Comment.builder()
                .userId(UUID.randomUUID())
                .content("Another example of a comment.")
                .roomId(UUID.randomUUID())
                .lastEditedBy(UUID.randomUUID())
                .build();

        Comment comment3 = Comment.builder()
                .userId(UUID.randomUUID())
                .content("Yet another comment for testing.")
                .roomId(UUID.randomUUID())
                .lastEditedBy(UUID.randomUUID())
                .build();

        List<Comment> comments = List.of(comment1, comment2, comment3);
        commentRepository.saveAll(comments);
        log.info("CommentDataSeeder - seeded comments.");
    }



}
