package com.tinqinacademy.comments.api.interfaces.room;

import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;

public interface RoomService {

    ReturnCommentOutput retrievesAllComments(ReturnCommentInput input);

    LeaveCommentOutput leaveComment(LeaveCommentInput input);

    EditCommentContentOutput updateContentComment(EditCommentContentInput input);
}
