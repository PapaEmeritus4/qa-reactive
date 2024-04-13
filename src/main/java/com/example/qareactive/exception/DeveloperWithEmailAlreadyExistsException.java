package com.example.qareactive.exception;

public class DeveloperWithEmailAlreadyExistsException extends ApiException {
    public DeveloperWithEmailAlreadyExistsException(String message, String errorCode) {
        super(message, errorCode);
    }
}
