package com.tinqinacademy.comments.core.services.systemImpl;

import com.tinqinacademy.comments.api.interfaces.system.SystemService;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger log = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Override
    public EditCommentAllOutput updateComment(EditCommentAllInput input) {
        log.info("Start updating all comment");
        EditCommentAllOutput output = EditCommentAllOutput.builder().id(input.getContentId()).build();
        log.info("End updating all comment");
        return output;
    }

    @Override
    public DeleteCommentOutput deleteComment(DeleteCommentInput input) {
        log.info("Start deleting all comment");
        DeleteCommentOutput output = DeleteCommentOutput.builder().build();
        log.info("End deleting all comment");
        return output;
    }
}
