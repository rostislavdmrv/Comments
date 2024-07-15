package com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom;

import com.tinqinacademy.comments.api.models.output.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentOutput {

    private List<Comment> comments;
}
