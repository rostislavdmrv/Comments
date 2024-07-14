package com.tinqinacademy.comments.rest.controllers.hotel;

import com.tinqinacademy.comments.api.interfaces.room.RoomService;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hotel")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @Operation(summary = "Retrieves all comments for a certain room", description = "Retrieves all comments associated with a specific room based on the provided room ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room ID provided"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to access comments for this room"),
            @ApiResponse(responseCode = "404", description = "Room or comments not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{roomId}/comment")
    public ResponseEntity<ReturnCommentOutput> retrievesAllComments(@PathVariable String roomId){

        ReturnCommentInput input = ReturnCommentInput.builder().roomId(roomId).build();
        ReturnCommentOutput result = roomService.retrievesAllComments(input);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/{roomId}/comment")
    public ResponseEntity<LeaveCommentOutput> leaveComment(@PathVariable String roomId, @RequestBody LeaveCommentInput input){
        LeaveCommentInput  updatedInput = input.toBuilder().roomId(roomId).build();
        LeaveCommentOutput output = roomService.leaveComment(updatedInput);
        return new ResponseEntity<>(output, HttpStatus.OK);

    }

    @PatchMapping("/{commentId}/comment")
    public ResponseEntity<EditCommentContentOutput> updateContentComment(@PathVariable("commentId") String commentId, @RequestBody EditCommentContentInput input) {

        EditCommentContentInput updatedInput = input.toBuilder().contentId(commentId).build();

        EditCommentContentOutput result = roomService.updateContentComment(updatedInput);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
