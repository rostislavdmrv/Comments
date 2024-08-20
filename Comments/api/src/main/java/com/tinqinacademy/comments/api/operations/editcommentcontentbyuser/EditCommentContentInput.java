package com.tinqinacademy.comments.api.operations.editcommentcontentbyuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EditCommentContentInput implements OperationInput {

    @JsonIgnore
    @NotBlank(message = "commentId cannot be blank")
    @UUID
    private String commentId;

    @JsonIgnore
    @NotBlank
    @UUID
    private String userId;

    @NotBlank(message = "Content name cannot be blank")
    @Size(min =2,max = 100, message = "content name cannot exceed 100 characters")
    private String content;
}
