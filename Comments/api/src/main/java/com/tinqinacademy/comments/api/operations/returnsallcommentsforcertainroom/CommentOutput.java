package com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentOutput {

    private String id;
    private String firstName;
    private String lastName;
    private String content;
    private LocalDate publishDate;
    private  LocalDate lastEditedDate;
    private String lastEditedBy;

}
