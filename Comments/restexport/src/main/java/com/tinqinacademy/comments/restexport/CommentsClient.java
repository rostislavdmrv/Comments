package com.tinqinacademy.comments.restexport;

import com.tinqinacademy.comments.api.feignclientapiroutes.FeignClientApiRoutes;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@Headers({"Content-Type: application/json"})
public interface CommentsClient {

    @RequestLine(FeignClientApiRoutes.RETRIEVE_COMMENTS)
    ReturnCommentOutput retrievesAllComments(@Param String roomId);

    @RequestLine(FeignClientApiRoutes.LEAVE_COMMENT)
    LeaveCommentOutput leaveComment(@Param String roomId, LeaveCommentInput input);

    @RequestLine(FeignClientApiRoutes.UPDATE_COMMENT_CONTENT)
    EditCommentContentOutput updateContentComment(@Param String commentId, EditCommentContentInput input);

    @RequestLine(FeignClientApiRoutes.UPDATE_COMMENT_BY_ADMIN)
    EditCommentAllOutput updateComment(@Param String commentId, EditCommentAllInput input);

    @RequestLine(FeignClientApiRoutes.DELETE_COMMENT)
    DeleteCommentOutput deleteComment(@Param String commentId);
}
