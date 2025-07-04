package org.jcr.architectureportsandadapters.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase genérica para respuestas HTTP estandarizadas.
 * Proporciona una estructura consistente para todas las respuestas de la API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    
    /**
     * Indica si la operación fue exitosa.
     */
    private Boolean success;
    
    /**
     * Mensaje descriptivo de la operación.
     */
    private String message;
    
    /**
     * Los datos de la respuesta.
     */
    private T data;
    
    /**
     * Información adicional sobre errores.
     */
    private ErrorDetails error;
    
    /**
     * Timestamp de la respuesta.
     */
    private Long timestamp;
    
    /**
     * Crea una respuesta exitosa con datos.
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }
    
    /**
     * Crea una respuesta exitosa con datos y mensaje.
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }
    
    /**
     * Crea una respuesta de error con mensaje.
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }
    
    /**
     * Crea una respuesta de error con detalles.
     */
    public static <T> ApiResponse<T> error(String message, ErrorDetails error) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .timestamp(System.currentTimeMillis())
                .build();
    }
    
    /**
     * Clase interna para detalles de errores.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorDetails {
        private String code;
        private String detail;
        private String field;
    }
}
