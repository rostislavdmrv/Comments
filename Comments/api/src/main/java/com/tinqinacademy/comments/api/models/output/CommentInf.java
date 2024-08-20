package com.tinqinacademy.comments.api.models.output;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentInf {

    @NotBlank(message = "ID cannot be blank")
    private String id;

    @NotBlank
    @UUID
    private String userId;

    @NotBlank(message = "Content name cannot be blank")
    @Size(min =2,max = 100, message = "content name cannot exceed 100 characters")
    private String content;

    @NotNull(message = "Publish date cannot be null")
    @PastOrPresent(message = "Publish date cannot be in the future")
    private LocalDate publishDate;

    @NotNull(message = "Last edited date cannot be null")
    @PastOrPresent(message = "Last edited date cannot be in the future")
    private  LocalDate lastEditedDate;

    @NotBlank(message = "Last edited by someone cannot be blank")
    @Size(min =2,max = 20, message = "Last name cannot exceed 20 characters")
    private String lastEditedBy;

}


