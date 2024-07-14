package com.tinqinacademy.comments.api.interfaces.exception;

import com.tinqinacademy.comments.api.errors.ErrorWrapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorService {
    ErrorWrapper errorHandler(MethodArgumentNotValidException ex);
}
