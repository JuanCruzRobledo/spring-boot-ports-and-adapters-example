package org.jcr.architectureportsandadapters.shared.exception;

import org.jcr.architectureportsandadapters.shared.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manejador global de excepciones para la aplicación.
 * Captura y maneja las excepciones de manera centralizada.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    /**
     * Maneja la excepción UserNotFoundException.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFoundException(UserNotFoundException ex) {
        ApiResponse.ErrorDetails errorDetails = ApiResponse.ErrorDetails.builder()
                .code("USER_NOT_FOUND")
                .detail(ex.getMessage())
                .build();
        
        ApiResponse<Void> response = ApiResponse.error("Usuario no encontrado", errorDetails);
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Maneja excepciones genéricas de IllegalArgumentException.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse.ErrorDetails errorDetails = ApiResponse.ErrorDetails.builder()
                .code("INVALID_ARGUMENT")
                .detail(ex.getMessage())
                .build();
        
        ApiResponse<Void> response = ApiResponse.error("Argumento inválido", errorDetails);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Maneja excepciones genéricas no controladas.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        ApiResponse.ErrorDetails errorDetails = ApiResponse.ErrorDetails.builder()
                .code("INTERNAL_ERROR")
                .detail("Error interno del servidor")
                .build();
        
        ApiResponse<Void> response = ApiResponse.error("Error interno del servidor", errorDetails);
        
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
