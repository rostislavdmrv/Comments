package com.tinqinacademy.comments.api.operations.editcommentallbyadmin;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EditCommentAllInput {
    private String contentId;
    private String roomNo;
    private String firstName;
    private String lastName;
    private String content;
}
