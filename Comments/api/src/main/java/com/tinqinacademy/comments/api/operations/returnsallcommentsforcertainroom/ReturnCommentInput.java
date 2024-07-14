package com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentInput {

    private String roomId;
}
