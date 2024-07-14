package com.tinqinacademy.comments.api.interfaces.system;

import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;

public interface SystemService {

    EditCommentAllOutput updateComment(EditCommentAllInput input);

    DeleteCommentOutput deleteComment(DeleteCommentInput input);
}
