package org.jcr.architectureportsandadapters.port.in;

import org.jcr.architectureportsandadapters.domain.model.User;

import java.util.Optional;

public interface GetUserPort {
    Optional<User> findById(Long id);
}
