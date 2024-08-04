package com.tinqinacademy.comments.api.operations.editcommentcontentbyuser;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditCommentContentOutput implements OperationOutput {
    private String id;
}
