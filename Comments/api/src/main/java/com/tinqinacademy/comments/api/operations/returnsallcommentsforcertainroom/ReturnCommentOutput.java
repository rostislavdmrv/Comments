package com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentOutput {

    private List<CommentOutput> comments;
}
