package com.tinqinacademy.comments.core.exceptions;

import com.tinqinacademy.comments.api.interfaces.exception.ErrorService;
import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.models.error.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErrorServiceImpl implements ErrorService {
    @Override
    public ErrorWrapper errorHandler(MethodArgumentNotValidException ex) {
        List<ErrorsResponse> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(
                        ErrorsResponse.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .build()));

        return ErrorWrapper.builder()
                .errors(errors)
                .errorCode(HttpStatus.BAD_REQUEST).build();

    }
}
