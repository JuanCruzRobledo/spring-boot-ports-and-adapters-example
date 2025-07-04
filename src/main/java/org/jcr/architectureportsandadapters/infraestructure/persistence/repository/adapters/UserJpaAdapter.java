package org.jcr.architectureportsandadapters.infraestructure.persistence.repository.adapters;

import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.application.mapper.UserMapper;
import org.jcr.architectureportsandadapters.port.out.UserPersistencePort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.jcr.architectureportsandadapters.infraestructure.persistence.repository.jpa.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adaptador JPA para persistencia de usuarios.
 * Implementa el puerto de salida UserPersistencePort.
 */
@RequiredArgsConstructor
@Repository
public class UserJpaAdapter implements UserPersistencePort {

    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Guarda un usuario en la base de datos.
     * @param user Usuario a guardar.
     * @return Usuario guardado.
     */
    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userEntity = userRepository.save(userEntity);

        User userDomain = userMapper.toUserDomain(userEntity);

        return userDomain;
    }

    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario.
     * @return Usuario encontrado, si existe.
     */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDomain);
    }
}
