package com.tinqinacademy.comments.api.errors;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorWrapper {
    private List<ErrorsResponse> errors;
    private HttpStatus errorCode;
}
