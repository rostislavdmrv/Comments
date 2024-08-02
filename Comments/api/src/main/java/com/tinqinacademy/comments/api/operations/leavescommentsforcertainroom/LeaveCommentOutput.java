package com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveCommentOutput implements OperationOutput {
    private String id;
}
