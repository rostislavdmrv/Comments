package com.tinqinacademy.comments.api.operations.editcommentallbyadmin;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditCommentAllOutput implements OperationOutput {
    private String id;
}
