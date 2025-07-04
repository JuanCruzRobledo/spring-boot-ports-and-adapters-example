package org.jcr.architectureportsandadapters.domain.model;

/**
 * Entidad que representa un usuario en el dominio del negocio.
 * Utiliza record para implementar inmutabilidad.
 */
public record User(
        Long id,
        String nombre,
        String apellido
) {
}
