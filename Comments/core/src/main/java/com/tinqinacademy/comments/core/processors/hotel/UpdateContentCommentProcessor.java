package com.tinqinacademy.comments.core.processors.hotel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.comments.core.errorhandler.ErrorHandler;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentOperation;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class UpdateContentCommentProcessor extends BaseOperationProcessor<EditCommentContentInput, EditCommentContentOutput> implements EditCommentContentOperation {
    private final ObjectMapper objectMapper;

    protected UpdateContentCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository, ObjectMapper objectMapper) {
        super(conversionService, validator, errorHandler, commentRepository);
        this.objectMapper = objectMapper;
    }


    @Override
    public Either<ErrorWrapper, EditCommentContentOutput> process(EditCommentContentInput input) {
        log.info("Start updating content comment");

        return Try.of(() -> {
                    validateInput(input);
                    Comment comment = retrieveComment(input.getCommentId());
                    JsonNode patchedNode = applyPatchToComment(comment, input);
                    Comment patchedComment = objectMapper.treeToValue(patchedNode, Comment.class);
                    Comment saved = commentRepository.save(patchedComment);
                    EditCommentContentOutput output = conversionService.convert(saved, EditCommentContentOutput.class);
                    log.info("End updating content comment");
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private Comment retrieveComment(String contentId) {
        UUID commentId = UUID.fromString(contentId);
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId.toString()));
    }

    private JsonNode applyPatchToComment(Comment comment, EditCommentContentInput input) throws JsonPatchException, IOException {
        JsonNode commentNode = objectMapper.valueToTree(comment);
        JsonNode inputNode = objectMapper.valueToTree(input);

        JsonMergePatch patch = JsonMergePatch.fromJson(inputNode);
        return patch.apply(commentNode);
    }



}
