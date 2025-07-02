package org.example.accountservice.exception;

public class AccountServiceException extends RuntimeException {
    public AccountServiceException(String message) {
        super(message);
    }

    public static ResourceAlreadyExistsException alreadyExists(Class<?> clazz, String conflictValue) {
        return new ResourceAlreadyExistsException("%s already exists :: %s".formatted(clazz.getSimpleName(), conflictValue));
    }

    public static class ResourceAlreadyExistsException extends RuntimeException {
        public ResourceAlreadyExistsException(String message) {
            super(message);
        }
    }
}
