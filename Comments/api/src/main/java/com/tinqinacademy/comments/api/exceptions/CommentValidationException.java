package com.tinqinacademy.comments.api.exceptions;

import com.tinqinacademy.comments.api.models.error.ErrorsResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentValidationException extends RuntimeException{
    private final List<ErrorsResponse> violations;

    public CommentValidationException(List<ErrorsResponse> violations) {
        this.violations = violations;
    }
}
