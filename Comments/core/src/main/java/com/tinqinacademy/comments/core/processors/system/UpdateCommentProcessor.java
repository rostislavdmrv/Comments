package com.tinqinacademy.comments.core.processors.system;

import com.tinqinacademy.comments.core.errorhandler.ErrorHandler;
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
public class UpdateCommentProcessor extends BaseOperationProcessor<EditCommentAllInput, EditCommentAllOutput> implements EditCommentWholeOperation {


    protected UpdateCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        super(conversionService, validator, errorHandler, commentRepository);
    }

    @Override
    public Either<ErrorWrapper, EditCommentAllOutput> process(EditCommentAllInput input) {
        log.info("Start updating whole comment by admin");

        return Try.of(() -> {
                    validateInput(input);
                    Comment comment = retrieveComment(input.getCommentId());
                    Comment commentToUpdate = conversionService.convert(input, Comment.class);
                    commentToUpdate.setPublishDate(comment.getPublishDate());

                    Comment updatedComment = saveComment(commentToUpdate);
                    EditCommentAllOutput output = conversionService.convert(updatedComment, EditCommentAllOutput.class);
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


    private Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }


}
