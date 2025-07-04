package org.jcr.architectureportsandadapters.domain.model;

public record User(
        Long id,
        String nombre,
        String apellido
) {
}
