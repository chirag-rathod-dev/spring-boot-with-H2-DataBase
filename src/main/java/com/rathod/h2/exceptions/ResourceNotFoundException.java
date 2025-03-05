package com.rathod.h2.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Chirag-scaletech
 */
@ResponseStatus
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
