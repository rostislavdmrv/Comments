package com.tinqinacademy.comments.rest.controllers.hotel;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentOperation;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOperation;
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
@Tag(name = "Room - Comments - REST APIs")
public class RoomController extends BaseController {

    private final ReturnCommentOperation returnCommentOperation;
    private final LeaveCommentOperation leaveCommentOperation;
    private final EditCommentContentOperation editCommentContentOperation;


    @Operation(summary = "Retrieves all comments for a certain room", description = "Retrieves all comments associated with a specific room based on the provided room ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to access comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room or comments not found")
    })
    @GetMapping(RestApiRoutes.RETRIEVE_ALL_COMMENTS)
    public ResponseEntity<?> retrievesAllComments(@PathVariable String roomId){

        ReturnCommentInput input = ReturnCommentInput.builder()
                .roomId(roomId)
                .build();
        return handleWithStatus(returnCommentOperation.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Leave a comment for a specific room", description = "Allows users to leave a comment associated with a specific room based on the provided room ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID or comment content provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to leave comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @PostMapping(RestApiRoutes.LEAVE_COMMENT)
    public ResponseEntity<?> leaveComment(@PathVariable String roomId,@RequestBody LeaveCommentInput input){
        LeaveCommentInput  updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        return handleWithStatus(leaveCommentOperation.process(updatedInput), HttpStatus.OK);

    }
    @Operation(summary = "Update comment content for a specific room", description = "Allows users to update the content of an existing comment associated with a specific room based on the provided room ID and comment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID or comment content provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to update comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room or comment not found")
    })
    @PatchMapping(RestApiRoutes.UPDATE_COMMENT_CONTENT)
    public ResponseEntity<?> updateContentComment(@PathVariable("commentId") String commentId,@RequestBody EditCommentContentInput input) {

        EditCommentContentInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        return handleWithStatus(editCommentContentOperation.process(updatedInput), HttpStatus.OK);
    }

}
