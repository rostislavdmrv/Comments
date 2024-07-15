package com.tinqinacademy.comments.api.operations.deletecommentbyadmin;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteCommentInput {

    @NotBlank(message = "Commend ID cannot be blank")
    private String commendId;
}
