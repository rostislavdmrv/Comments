package com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom;

import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.models.output.CommentInf;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentOutput implements OperationOutput {

    private List<CommentInf> comments;
}
