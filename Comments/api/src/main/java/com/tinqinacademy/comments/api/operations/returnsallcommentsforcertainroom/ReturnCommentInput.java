package com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentInput implements OperationInput {

    @NotBlank(message = "roomId cannot be blank")
    @JsonIgnore
    private String roomId;
}
