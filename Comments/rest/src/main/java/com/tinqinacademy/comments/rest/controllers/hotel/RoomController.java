package com.tinqinacademy.comments.rest.controllers.hotel;

import com.tinqinacademy.comments.api.interfaces.room.RoomService;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import com.tinqinacademy.comments.rest.restapiroutes.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;


    @Operation(summary = "Retrieves all comments for a certain room", description = "Retrieves all comments associated with a specific room based on the provided room ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to access comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room or comments not found")
    })
    @GetMapping(RestApiRoutes.USER_RETRIEVE_ALL_COMMENTS)
    public ResponseEntity<ReturnCommentOutput> retrievesAllComments(@PathVariable String roomId){

        ReturnCommentInput input = ReturnCommentInput.builder()
                .roomId(roomId)
                .build();
        ReturnCommentOutput result = roomService.retrievesAllComments(input);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Operation(summary = "Leave a comment for a specific room", description = "Allows users to leave a comment associated with a specific room based on the provided room ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID or comment content provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to leave comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @PostMapping(RestApiRoutes.USER_LEAVE_COMMENT)
    public ResponseEntity<LeaveCommentOutput> leaveComment(@PathVariable String roomId, @Valid @RequestBody LeaveCommentInput input){
        LeaveCommentInput  updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        LeaveCommentOutput output = roomService.leaveComment(updatedInput);
        return new ResponseEntity<>(output, HttpStatus.OK);

    }

    @Operation(summary = "Update comment content for a specific room", description = "Allows users to update the content of an existing comment associated with a specific room based on the provided room ID and comment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID or comment content provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to update comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room or comment not found")
    })
    @PatchMapping(RestApiRoutes.USER_UPDATE_COMMENT_CONTENT)
    public ResponseEntity<EditCommentContentOutput> updateContentComment(@PathVariable("commentId") String commentId,@Valid @RequestBody EditCommentContentInput input) {

        EditCommentContentInput updatedInput = input.toBuilder()
                .contentId(commentId)
                .build();

        EditCommentContentOutput result = roomService.updateContentComment(updatedInput);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
