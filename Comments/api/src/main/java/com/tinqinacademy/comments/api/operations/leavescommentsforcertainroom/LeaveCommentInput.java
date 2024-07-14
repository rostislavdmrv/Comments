package com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LeaveCommentInput {

    private String roomId;
    private String firstName;
    private String lastName;
    private String content;
}
