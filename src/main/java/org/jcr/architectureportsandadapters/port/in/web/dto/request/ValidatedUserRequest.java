package org.jcr.architectureportsandadapters.port.in.web.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO validado para las peticiones de creación de usuario.
 * Incluye validaciones de entrada para garantizar la integridad de los datos.
 */
public record ValidatedUserRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,
        
        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
        String apellido
) {
}
