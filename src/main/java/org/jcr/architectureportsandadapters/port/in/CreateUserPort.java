package org.jcr.architectureportsandadapters.port.in;

import org.jcr.architectureportsandadapters.domain.model.User;

public interface CreateUserPort {
    User createUser(User user);
}
