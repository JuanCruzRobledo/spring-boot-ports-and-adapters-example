package org.jcr.architectureportsandadapters.port.out;

import org.jcr.architectureportsandadapters.domain.model.User;

import java.util.Optional;

/**
 * Puerto de salida para persistencia de usuarios.
 * Define el contrato para operaciones de persistencia de usuarios.
 */
public interface UserPersistencePort {
    /**
     * Guarda un usuario en la base de datos.
     * @param user Usuario a guardar.
     * @return Usuario guardado.
     */
    User save(User user);
    
    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario.
     * @return Usuario encontrado, si existe.
     */
    Optional<User> findById(Long id);
}
