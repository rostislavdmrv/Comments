package com.tinqinacademy.comments.core.services.systemImpl;

import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import com.tinqinacademy.comments.api.interfaces.system.SystemService;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {

    private final CommentRepository commentRepository;

    @Override
    public EditCommentAllOutput updateComment(EditCommentAllInput input) {
        log.info("Start updating whole comment by admin");
        UUID commentId = UUID.fromString(input.getCommentId());

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment"," commentId",commentId.toString()));

        comment.setFirstName(input.getFirstName());
        comment.setLastName(input.getLastName());
        comment.setContent(input.getContent());
        comment.setLastEditedDate(LocalDateTime.now());
        comment.setLastEditedBy(UUID.randomUUID());

        Comment updatedComment = commentRepository.save(comment);

        EditCommentAllOutput output = EditCommentAllOutput.builder()
                .id(updatedComment.getId().toString())
                .build();

        log.info("End updating whole comment by admin");
        return output;

    }

    @Override
    public DeleteCommentOutput deleteComment(DeleteCommentInput input) {
        log.info("Start deleting whole comment by admin");
        UUID commentId = UUID.fromString(input.getCommendId());

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", " commendId",commentId.toString()));

        commentRepository.delete(comment);
        log.info("Comment with ID {} has been deleted", commentId);

        DeleteCommentOutput output = DeleteCommentOutput.builder().build();

        log.info("End deleting whole comment by admin");
        return output;
    }

}