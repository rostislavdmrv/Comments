package com.tinqinacademy.comments.api.exceptions;

import com.tinqinacademy.comments.api.exceptions.messages.Messages;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValueString;


    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString) {
        super(String.format(Messages.RESOURCE_NOT_FOUND, resourceName, fieldName, fieldValueString));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueString = fieldValueString;
    }
}
