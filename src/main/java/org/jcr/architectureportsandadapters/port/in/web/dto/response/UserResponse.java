package org.jcr.architectureportsandadapters.port.in.web.dto.response;

public record UserResponse(
        Long id,
        String nombre,
        String apellido
) {
}
