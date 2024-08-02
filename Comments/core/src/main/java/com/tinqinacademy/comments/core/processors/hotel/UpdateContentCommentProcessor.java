package com.tinqinacademy.comments.core.processors.hotel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.comments.api.errors.ErrorHandler;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentOperation;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentOutput;
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
public class UpdateContentCommentProcessor extends BaseOperationProcessor implements EditCommentContentOperation {

    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper;
    private final ErrorHandler errorHandler;


    protected UpdateContentCommentProcessor(ConversionService conversionService, Validator validator, CommentRepository commentRepository, ObjectMapper objectMapper, ErrorHandler errorHandler) {
        super(conversionService, validator);
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
        this.errorHandler = errorHandler;
    }

    @Override
    public Either<ErrorWrapper, EditCommentContentOutput> process(EditCommentContentInput input) {
        log.info("Start updating content comment");

        return Try.of(() -> {
                    Comment comment = retrieveComment(input.getContentId());
                    JsonNode patchedNode = applyPatchToComment(comment, input);
                    Comment patchedComment = objectMapper.treeToValue(patchedNode, Comment.class);
                    commentRepository.save(patchedComment);
                    EditCommentContentOutput output = buildEditCommentContentOutput(input.getContentId());
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

    private EditCommentContentOutput buildEditCommentContentOutput(String contentId) {
        return EditCommentContentOutput.builder()
                .id(contentId)
                .build();
    }

}
