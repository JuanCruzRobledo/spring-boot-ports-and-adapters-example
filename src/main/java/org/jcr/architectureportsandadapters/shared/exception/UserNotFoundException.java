package org.jcr.architectureportsandadapters.shared.exception;

/**
 * Excepción que se lanza cuando no se encuentra un usuario.
 */
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UserNotFoundException(Long userId) {
        super("Usuario con ID " + userId + " no encontrado");
    }
}
