package com.tinqinacademy.comments.rest.controllers.system;

import com.tinqinacademy.comments.api.interfaces.system.SystemService;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system")
public class SystemController {

    private final SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<EditCommentAllOutput> updateComment(@PathVariable("commentId") String commentId,@RequestBody EditCommentAllInput input) {

        EditCommentAllInput updatedInput = input.toBuilder().contentId(commentId).build();

        EditCommentAllOutput result = systemService.updateComment(updatedInput);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<DeleteCommentOutput> deleteComment(@PathVariable String commentId) {

        DeleteCommentInput commentForDelete = DeleteCommentInput.builder().commendId(commentId).build();

        DeleteCommentOutput result =systemService.deleteComment(commentForDelete);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


}
