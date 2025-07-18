package org.example.userservice.exception;

import java.util.UUID;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }

    public static ResourceNotFoundException notFound(Class<?> clazz, UUID id) {
        return new ResourceNotFoundException("%s not found :: %s".formatted(clazz.getSimpleName(), id));
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
