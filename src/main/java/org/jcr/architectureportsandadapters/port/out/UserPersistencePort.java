package org.jcr.architectureportsandadapters.port.out;

import org.jcr.architectureportsandadapters.domain.model.User;

import java.util.Optional;

public interface UserPersistencePort {
    User save(User user);
    Optional<User> findById(Long id);
}
