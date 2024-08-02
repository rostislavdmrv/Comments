package com.tinqinacademy.comments.api.operations.deletecommentbyadmin;

import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteCommentInput implements OperationInput {

    @NotBlank(message = "Commend ID cannot be blank")
    private String commendId;
}
