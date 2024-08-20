package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.models.output.CommentInf;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToCommentInfConverter implements Converter<Comment, CommentInf> {
    @Override
    public CommentInf convert(Comment source) {
        log.info("Start convert comment !");
        CommentInf output = CommentInf.builder()
                .id(source.getId().toString())
                .userId(source.getUserId().toString())
                .content(source.getContent())
                .lastEditedDate(source.getLastEditedDate().toLocalDate())
                .publishDate(source.getPublishDate().toLocalDate())
                .lastEditedBy(source.getLastEditedBy().toString())
                .build();
        log.info("End convert comment !");
        return output;
    }
}
