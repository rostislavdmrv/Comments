package com.tinqinacademy.comments.api.operations.editcommentallbyadmin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EditCommentAllInput {
    @NotBlank(message = "Comment ID cannot be blank")
    private String contentId;

    @NotBlank(message = "Room number cannot be blank")
    @Size(min = 1, max = 10, message = "Room number cannot exceed 10 characters" )
    private String roomNo;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2,max = 30, message = "First name cannot exceed 30 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min =2,max = 30, message = "Last name cannot exceed 30 characters")
    private String lastName;

    @NotBlank(message = "Content name cannot be blank")
    @Size(min =2,max = 100, message = "content name cannot exceed 100 characters")
    private String content;
}
