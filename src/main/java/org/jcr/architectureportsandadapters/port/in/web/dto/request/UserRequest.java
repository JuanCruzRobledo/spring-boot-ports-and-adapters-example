package org.jcr.architectureportsandadapters.port.in.web.dto.request;

/**
 * DTO para las peticiones de creación de usuario.
 * Contiene los datos necesarios para crear un nuevo usuario.
 */
public record UserRequest(
        String nombre,
        String apellido
) {
}
