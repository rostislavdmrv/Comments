package com.tinqinacademy.comments.api.operations.editcommentallbyadmin;

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
public class EditCommentAllInput implements OperationInput {

    @JsonIgnore
    private String commentId;

    @UUID(message = "Room id must be in  UUID syntax")
    @NotBlank(message = "Room id not provided")
    private String roomId;



    @UUID(message = "User id must be in UUID syntax")
    private String userId;

    @NotBlank(message = "Content name cannot be blank")
    @Size(min =2,max = 100, message = "content name cannot exceed 100 characters")
    private String content;
}
