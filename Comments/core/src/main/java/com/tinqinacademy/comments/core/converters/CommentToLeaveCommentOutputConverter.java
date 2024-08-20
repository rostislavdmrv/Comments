package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToLeaveCommentOutputConverter implements Converter<Comment, LeaveCommentOutput> {
    @Override
    public LeaveCommentOutput convert(Comment source) {
        log.info("Start converting from Comment to LeaveCommentOutput with input: {}", source);

        LeaveCommentOutput output = LeaveCommentOutput.builder()
                .id(source.getId().toString())
                .build();

        log.info("End converting from Comment to LeaveCommentOutput with output: {}", output);
        return output;
    }
}
