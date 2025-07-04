package org.jcr.architectureportsandadapters.port.in;

import org.jcr.architectureportsandadapters.domain.model.User;

/**
 * Puerto de entrada para crear usuarios.
 * Define el contrato para crear un nuevo usuario en el sistema.
 */
public interface CreateUserPort {
    /**
     * Crea un nuevo usuario.
     * @param user Usuario a crear.
     * @return Usuario creado.
     */
    User createUser(User user);
}
