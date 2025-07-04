package org.jcr.architectureportsandadapters.application.service;

import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.port.in.CreateUserPort;
import org.jcr.architectureportsandadapters.port.in.GetUserPort;
import org.jcr.architectureportsandadapters.port.out.UserPersistencePort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements CreateUserPort, GetUserPort {

    private final UserPersistencePort userPersistencePort;

    @Override
    public User createUser(User user) {
        return userPersistencePort.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userPersistencePort.findById(id);
    }
}
