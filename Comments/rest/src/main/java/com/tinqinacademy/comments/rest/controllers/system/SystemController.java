package com.tinqinacademy.comments.rest.controllers.system;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOperation;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentWholeOperation;
import com.tinqinacademy.comments.rest.controllers.base.BaseController;
import com.tinqinacademy.comments.api.restapiroutes.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "System - Comments - REST APIs")
public class SystemController extends BaseController {

    private final EditCommentWholeOperation editCommentWholeOperation;
    private final DeleteCommentOperation deleteCommentOperation;


    @Operation(summary = "Update a comment for a specific room by admin", description = "Allows admins to update an existing comment associated with a specific room based on the provided room ID and comment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID or comment content provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to update comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room or comment not found")
    })

    @PutMapping(RestApiRoutes.UPDATE_COMMENT)
    public ResponseEntity<?> updateComment(@PathVariable("commentId") String commentId,@RequestBody EditCommentAllInput input) {

        EditCommentAllInput updatedInput = input.toBuilder().commentId(commentId).build();
        return handleWithStatus(editCommentWholeOperation.process(updatedInput), HttpStatus.OK);

    }

    @Operation(summary = "Delete a comment by an admin", description = "Allows an admin to delete a comment associated with a specific room based on the provided room ID and comment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID or comment ID provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to delete comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room or comment not found")
    })
    @DeleteMapping(RestApiRoutes.DELETE_COMMENT)
    public ResponseEntity<?> deleteComment(@PathVariable String commentId) {

        DeleteCommentInput commentForDelete = DeleteCommentInput.builder().commendId(commentId).build();
        return handleWithStatus(deleteCommentOperation.process(commentForDelete), HttpStatus.OK);
    }

}
