package org.jcr.architectureportsandadapters.port.in.web.dto.request;

public record UserRequest(
        String nombre,
        String apellido
) {
}
