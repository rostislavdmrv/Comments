package com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom;

import com.tinqinacademy.comments.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentInput implements OperationInput {

    private String roomId;
}
