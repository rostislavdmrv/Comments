package com.tinqinacademy.comments.core.services.systemImpl;

import com.tinqinacademy.comments.api.interfaces.system.SystemService;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {


    @Override
    public EditCommentAllOutput updateComment(EditCommentAllInput input) {
        log.info("Start updating whole comment by admin");
        EditCommentAllOutput output = EditCommentAllOutput.builder()
                .id(input.getContentId())
                .build();
        log.info("End updating whole comment by admin");
        return output;
    }

    @Override
    public DeleteCommentOutput deleteComment(DeleteCommentInput input) {
        log.info("Start deleting whole comment by admin");
        DeleteCommentOutput output = DeleteCommentOutput.builder().build();
        log.info("End deleting whole comment by admin");
        return output;
    }
}
