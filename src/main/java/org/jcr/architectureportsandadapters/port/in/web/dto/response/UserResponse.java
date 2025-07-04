package org.jcr.architectureportsandadapters.port.in.web.dto.response;

/**
 * DTO para las respuestas de información de usuario.
 * Contiene los datos del usuario para ser devueltos al cliente.
 */
public record UserResponse(
        Long id,
        String nombre,
        String apellido
) {
}
