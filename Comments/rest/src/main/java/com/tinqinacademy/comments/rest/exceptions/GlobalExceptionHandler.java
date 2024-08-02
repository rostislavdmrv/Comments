package com.tinqinacademy.comments.rest.exceptions;

import com.tinqinacademy.comments.api.models.error.ErrorWrapper;
import com.tinqinacademy.comments.api.interfaces.exception.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorWrapper errorWrappers = errorService.errorHandler(ex);
        return new ResponseEntity<>(errorWrappers.getErrors(), errorWrappers.getErrorCode());
    }
}
