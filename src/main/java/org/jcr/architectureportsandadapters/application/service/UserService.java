package org.jcr.architectureportsandadapters.application.service;

import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.port.in.CreateUserPort;
import org.jcr.architectureportsandadapters.port.in.GetUserPort;
import org.jcr.architectureportsandadapters.port.out.UserPersistencePort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio de aplicación para gestionar la lógica de negocio relacionada con los usuarios.
 */
@RequiredArgsConstructor
@Service
public class UserService implements CreateUserPort, GetUserPort {

    private final UserPersistencePort userPersistencePort;

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param user Objeto de dominio de usuario a crear.
     * @return Usuario creado.
     */
    @Override
    public User createUser(User user) {
        return userPersistencePort.save(user);
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id ID del usuario a buscar.
     * @return Usuario encontrado, si existe.
     */
    @Override
    public Optional<User> findById(Long id) {
        return userPersistencePort.findById(id);
    }
}
