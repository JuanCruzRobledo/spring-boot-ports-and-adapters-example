package org.jcr.architectureportsandadapters.infraestructure.persistence.repository.jpa;

import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
}
