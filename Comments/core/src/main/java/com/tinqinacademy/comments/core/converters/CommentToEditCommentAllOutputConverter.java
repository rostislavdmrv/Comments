package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToEditCommentAllOutputConverter implements Converter<Comment, EditCommentAllOutput> {
    @Override
    public EditCommentAllOutput convert(Comment source) {
        log.info("Start converting from Comment to EditCommentAllOutput with input: {}", source);

        EditCommentAllOutput output = EditCommentAllOutput.builder()
                .id(source.getId().toString())
                .build();

        log.info("End converting from Comment to EditCommentAllOutput with output: {}", output);
        return output;
    }
}
