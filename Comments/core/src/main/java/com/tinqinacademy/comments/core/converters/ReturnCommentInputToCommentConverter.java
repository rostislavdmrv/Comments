package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.models.output.CommentInf;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReturnCommentInputToCommentConverter implements Converter<ReturnCommentInput, Comment.CommentBuilder> {

    @Override
    public Comment.CommentBuilder convert(ReturnCommentInput source) {
        return Comment.builder();
    }
}
