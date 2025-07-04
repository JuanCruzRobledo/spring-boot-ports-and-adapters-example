package org.jcr.architectureportsandadapters.infraestructure.persistence.repository.adapters;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.application.mapper.UserMapper;
import org.jcr.architectureportsandadapters.port.out.UserPersistencePort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.jcr.architectureportsandadapters.infraestructure.persistence.repository.jpa.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserJpaAdapter implements UserPersistencePort {

    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userEntity = userRepository.save(userEntity);

        User userDomain = userMapper.toUserDomain(userEntity);

        return userDomain;
    }

    @Override
    public Optional<User> findById(Long id) {
        final UserEntity allUsers = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se pudo encontrar el usuario"));
        return Optional.of(userMapper.toUserDomain(allUsers));
    }
}
