package com.tinqinacademy.comments.core.processors.base;

import com.tinqinacademy.comments.api.base.OperationInput;
import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.exceptions.CommentValidationException;
import com.tinqinacademy.comments.api.models.error.ErrorsResponse;
import com.tinqinacademy.comments.core.errorhandler.ErrorHandler;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class BaseOperationProcessor <I extends OperationInput, O extends OperationOutput> {
    protected final ConversionService conversionService;
    protected final Validator validator;
    protected final ErrorHandler errorHandler;
    protected final CommentRepository commentRepository;

    protected BaseOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        this.conversionService = conversionService;
        this.validator = validator;
        this.errorHandler = errorHandler;
        this.commentRepository = commentRepository;
    }


    protected void validateInput(OperationInput input) {
        Set<ConstraintViolation<OperationInput>> violations = validator.validate(input);

        if (!violations.isEmpty()) {
            List<ErrorsResponse> errors = buildErrors(violations);

            throw new CommentValidationException(errors);
        }
    }

    private List<ErrorsResponse> buildErrors(Set<ConstraintViolation<OperationInput>> violations) {
        List<ErrorsResponse> errors = new ArrayList<>();
        for (ConstraintViolation<OperationInput> violation : violations) {
            errors.add(ErrorsResponse.builder()
                    .field(violation.getPropertyPath().toString())
                    .message(violation.getMessage())
                    .build());
        }
        return errors;
    }
}
