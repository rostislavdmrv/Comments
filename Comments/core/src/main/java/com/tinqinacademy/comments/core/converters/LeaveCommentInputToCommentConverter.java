package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class LeaveCommentInputToCommentConverter implements Converter<LeaveCommentInput, Comment> {
    @Override
    public Comment convert(LeaveCommentInput source) {
        log.info("Converting LeaveCommentInput to Comment");
        Comment comment = Comment.builder()
                .roomId(UUID.fromString(source.getRoomId()))
                .userId(UUID.fromString(source.getUserId()))
                .content(source.getContent())
                .lastEditedBy(UUID.fromString(source.getUserId()))
                .build();
        log.info("Converted LeaveCommentInput to Comment");
        return comment;
    }
}
