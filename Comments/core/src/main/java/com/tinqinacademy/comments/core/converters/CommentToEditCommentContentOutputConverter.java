package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToEditCommentContentOutputConverter implements Converter<Comment, EditCommentContentOutput> {

    @Override
    public EditCommentContentOutput convert(Comment source) {
        log.info("Start converting from Comment to EditCommentContentOutput with input: {}", source);

        EditCommentContentOutput output = EditCommentContentOutput.builder()
                .id(source.getId().toString())
                .build();

        log.info("End converting from Comment to EditCommentContentOutput with output: {}", output);
        return output;
    }
}
