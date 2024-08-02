package com.tinqinacademy.comments.core.services.hotelImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import com.tinqinacademy.comments.api.interfaces.room.RoomService;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.api.models.output.CommentInf;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements RoomService {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;
    private final ObjectMapper objectMapper;


    @Override
    public ReturnCommentOutput retrievesAllComments(ReturnCommentInput input) {
        log.info("Start retrieving all the comments for certain room");

        List<Comment> comments = commentRepository.findByRoomId(UUID.fromString(input.getRoomId()));

        List<CommentInf> commentInputs = comments.stream()
                .map(comment -> conversionService.convert(comment, CommentInf.class))
                .toList();


        ReturnCommentOutput output = ReturnCommentOutput.builder()
                .comments(commentInputs)
                .build();
        log.info("End retrieving all the comments for certain room");
        return output;
    }


    @Override
    public LeaveCommentOutput leaveComment(LeaveCommentInput input) {

        log.info("Start leaving comment");
        Comment comment =conversionService.convert(input, Comment.class);
        assert comment != null;
        Comment savedComment = commentRepository.save(comment);

        LeaveCommentOutput output = LeaveCommentOutput.builder()
                .id(savedComment.getId().toString())
                .build();
        log.info("End leaving comment");

        return output;
    }

    @Override
    public EditCommentContentOutput updateContentComment(EditCommentContentInput input) {
        log.info("Start updating content comment");
        UUID commentId = UUID.fromString(input.getContentId());
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId.toString()));


        JsonNode commentNode = objectMapper.valueToTree(comment);

        JsonNode inputNode = objectMapper.valueToTree(input);

        try {
            JsonMergePatch patch = JsonMergePatch.fromJson(inputNode);
            JsonNode patchedNode = patch.apply(commentNode);

            Comment patchedComment = objectMapper.treeToValue(patchedNode, Comment.class);

            commentRepository.save(patchedComment);

            EditCommentContentOutput output = EditCommentContentOutput.builder()
                    .id(input.getContentId())
                    .build();
            log.info("End updating content comment");
            return output;
        } catch (JsonPatchException | IOException e) {
            throw new RuntimeException("Failed to apply patch to comment: " + e.getMessage(), e);
        }

    }


}
