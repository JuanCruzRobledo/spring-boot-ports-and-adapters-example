package org.jcr.architectureportsandadapters.port.in;

import org.jcr.architectureportsandadapters.domain.model.User;

import java.util.Optional;

/**
 * Puerto de entrada para obtener usuarios.
 * Define el contrato para consultar usuarios en el sistema.
 */
public interface GetUserPort {
    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario.
     * @return Usuario encontrado, si existe.
     */
    Optional<User> findById(Long id);
}
