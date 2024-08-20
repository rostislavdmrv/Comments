package com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LeaveCommentInput  implements OperationInput {


    @JsonIgnore
    private String roomId;

    @NotBlank(message = "User id cannot be blank")
    private String userId;

    @NotBlank(message = "Content name cannot be blank")
    @Size(min =2,max = 100, message = "content name cannot exceed 100 characters")
    private String content;
}
