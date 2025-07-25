package org.example.accountservice.exception;

import java.util.UUID;

public class AccountServiceException extends RuntimeException {
    public AccountServiceException(String message) {
        super(message);
    }

    public static ResourceAlreadyExistsException alreadyExists(Class<?> clazz, String conflictValue) {
        return new ResourceAlreadyExistsException("%s already exists :: %s".formatted(clazz.getSimpleName(), conflictValue));
    }

    public static ResourceNotFoundException notFound(Class<?> clazz, UUID id) {
        return new ResourceNotFoundException("%s not found :: %s".formatted(clazz.getSimpleName(), id));
    }

    public static ActionNotAllowedException actionNotAllowed(Class<?> clazz, String reason) {
        return new ActionNotAllowedException("Action on %s not allowed :: %s".formatted(clazz.getSimpleName(), reason));
    }

    public static class ResourceAlreadyExistsException extends RuntimeException {
        public ResourceAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class ActionNotAllowedException extends RuntimeException {
        public ActionNotAllowedException(String message) {
            super(message);
        }
    }
}
