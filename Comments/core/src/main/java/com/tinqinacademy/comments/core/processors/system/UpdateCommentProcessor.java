package com.tinqinacademy.comments.core.processors.system;

import com.tinqinacademy.comments.api.errors.ErrorHandler;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentWholeOperation;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class UpdateCommentProcessor extends BaseOperationProcessor implements EditCommentWholeOperation {

    private final CommentRepository commentRepository;
    private final ErrorHandler errorHandler;

    protected UpdateCommentProcessor(ConversionService conversionService, Validator validator, CommentRepository commentRepository, ErrorHandler errorHandler) {
        super(conversionService, validator);
        this.commentRepository = commentRepository;
        this.errorHandler = errorHandler;
    }

    @Override
    public Either<ErrorWrapper, EditCommentAllOutput> process(EditCommentAllInput input) {
        log.info("Start updating whole comment by admin");

        return Try.of(() -> {
                    Comment comment = retrieveComment(input.getCommentId());
                    updateCommentDetails(comment, input);
                    Comment updatedComment = saveComment(comment);

                    EditCommentAllOutput output = buildEditCommentAllOutput(updatedComment);

                    log.info("End updating whole comment by admin");
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

    private void updateCommentDetails(Comment comment, EditCommentAllInput input) {
        comment.setFirstName(input.getFirstName());
        comment.setLastName(input.getLastName());
        comment.setContent(input.getContent());
        comment.setLastEditedDate(LocalDateTime.now());
        comment.setLastEditedBy(UUID.randomUUID());
    }

    private Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    private EditCommentAllOutput buildEditCommentAllOutput(Comment updatedComment) {
        return EditCommentAllOutput.builder()
                .id(updatedComment.getId().toString())
                .build();
    }
}
