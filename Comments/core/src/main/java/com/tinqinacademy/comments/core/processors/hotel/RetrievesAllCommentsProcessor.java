package com.tinqinacademy.comments.core.processors.hotel;

import com.tinqinacademy.comments.core.errorhandler.ErrorHandler;
import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.models.output.CommentInf;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOperation;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RetrievesAllCommentsProcessor extends BaseOperationProcessor<ReturnCommentInput, ReturnCommentOutput> implements ReturnCommentOperation {


    protected RetrievesAllCommentsProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        super(conversionService, validator, errorHandler, commentRepository);
    }

    @Override
    public Either<ErrorWrapper, ReturnCommentOutput> process(ReturnCommentInput input) {
        log.info("Start retrieving all the comments for certain room");

        return Try.of(() -> {
                    validateInput(input);
                    List<Comment> comments = retrieveComments(input);
                    List<CommentInf> commentInputs = convertCommentsToCommentInf(comments);

                    ReturnCommentOutput output = ReturnCommentOutput.builder()
                            .comments(commentInputs)
                            .build();

                    log.info("End retrieving all the comments for certain room {}", output);
                    return output;

                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private List<Comment> retrieveComments(ReturnCommentInput input) {
        return commentRepository.findByRoomId(UUID.fromString(input.getRoomId()));
    }

    private List<CommentInf> convertCommentsToCommentInf(List<Comment> comments) {
        return comments.stream()
                .map(comment -> conversionService.convert(comment, CommentInf.class))
                .toList();
    }

}
