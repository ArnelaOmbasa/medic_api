package com.example.api_medic.core.exceptions.repository;

import com.example.api_medic.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GeneralException {
    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);

    }

    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND.value());
    }
}
