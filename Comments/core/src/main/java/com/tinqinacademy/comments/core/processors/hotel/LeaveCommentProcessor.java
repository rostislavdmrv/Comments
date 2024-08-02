package com.tinqinacademy.comments.core.processors.hotel;

import com.tinqinacademy.comments.api.errors.ErrorHandler;
import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LeaveCommentProcessor extends BaseOperationProcessor implements LeaveCommentOperation {

    private final CommentRepository commentRepository;
    private final ErrorHandler errorHandler;

    protected LeaveCommentProcessor(ConversionService conversionService, Validator validator, CommentRepository commentRepository, ErrorHandler errorHandler) {
        super(conversionService, validator);
        this.commentRepository = commentRepository;
        this.errorHandler = errorHandler;
    }

    @Override
    public Either<ErrorWrapper, LeaveCommentOutput> process(LeaveCommentInput input) {
        log.info("Start leaving comment");

        return Try.of(() -> {
                    Comment comment = convertInputToComment(input);
                    Comment savedComment = saveComment(comment);
                    LeaveCommentOutput output = buildLeaveCommentOutput(savedComment);
                    log.info("End leaving comment");
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private Comment convertInputToComment(LeaveCommentInput input) {
        Comment comment = conversionService.convert(input, Comment.class);
        if (comment == null) {
            throw new IllegalStateException("Conversion of input to Comment failed");
        }
        return comment;
    }

    private Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    private LeaveCommentOutput buildLeaveCommentOutput(Comment savedComment) {
        return LeaveCommentOutput.builder()
                .id(savedComment.getId().toString())
                .build();
    }
}
