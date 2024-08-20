package com.tinqinacademy.comments.core.processors.hotel;

import com.tinqinacademy.comments.core.errorhandler.ErrorHandler;
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
public class LeaveCommentProcessor extends BaseOperationProcessor<LeaveCommentInput, LeaveCommentOutput> implements LeaveCommentOperation {


    protected LeaveCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        super(conversionService, validator, errorHandler, commentRepository);
    }

    @Override
    public Either<ErrorWrapper, LeaveCommentOutput> process(LeaveCommentInput input) {
        log.info("Start leaving comment");

        return Try.of(() -> {
                    validateInput(input);
                    Comment comment = convertInputToComment(input);
                    Comment savedComment = saveComment(comment);
                    LeaveCommentOutput output = conversionService.convert(savedComment, LeaveCommentOutput.class);
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

}

