package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class EditCommentAllInputToCommentConverter implements Converter<EditCommentAllInput, Comment> {
    @Override
    public Comment convert(EditCommentAllInput source) {
        log.info("Start converting from EditCommentAllInput to Comment with input: {}", source);

        Comment output = Comment.builder()
                .id(UUID.fromString(source.getCommentId()))
                .roomId(UUID.fromString(source.getRoomId()))
                .userId(UUID.fromString(source.getUserId()))
                .content(source.getContent())
                .lastEditedBy(UUID.fromString(source.getUserId()))
                .lastEditedDate(LocalDateTime.now())
                .build();

        log.info("Start converting from EditCommentAllInput to Comment with output: {}", output);
        return output;
    }
}
