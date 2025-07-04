package org.jcr.architectureportsandadapters.infraestructure.persistence.repository.jpa;

import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para operaciones CRUD con usuarios.
 * Extiende JpaRepository para obtener operaciones básicas.
 */
public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
}
