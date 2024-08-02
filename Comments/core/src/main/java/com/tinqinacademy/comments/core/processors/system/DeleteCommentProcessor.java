package com.tinqinacademy.comments.core.processors.system;

import com.tinqinacademy.comments.api.errors.ErrorHandler;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOperation;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOutput;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DeleteCommentProcessor extends BaseOperationProcessor implements DeleteCommentOperation {

    private final CommentRepository commentRepository;
    private final ErrorHandler errorHandler;

    protected DeleteCommentProcessor(ConversionService conversionService, Validator validator, CommentRepository commentRepository, ErrorHandler errorHandler) {
        super(conversionService, validator);
        this.commentRepository = commentRepository;
        this.errorHandler = errorHandler;
    }

    @Override
    public Either<ErrorWrapper, DeleteCommentOutput> process(DeleteCommentInput input) {
        log.info("Start deleting whole comment by admin");

        return Try.of(() -> {
                    Comment comment = retrieveComment(input.getCommendId());
                    deleteComment(comment);
                    DeleteCommentOutput output = buildDeleteCommentOutput();
                    log.info("Ended: Comment with ID {} has been deleted", comment.getId());
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
    private Comment retrieveComment(String commentId) {
        UUID id = UUID.fromString(commentId);
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", id.toString()));
    }

    private void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    private DeleteCommentOutput buildDeleteCommentOutput() {
        return DeleteCommentOutput.builder().build();
    }
}
