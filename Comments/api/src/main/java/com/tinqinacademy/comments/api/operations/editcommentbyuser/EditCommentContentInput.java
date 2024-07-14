package com.tinqinacademy.comments.api.operations.editcommentbyuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EditCommentContentInput {

    @JsonIgnore
    private String contentId;

    private String content;
}
