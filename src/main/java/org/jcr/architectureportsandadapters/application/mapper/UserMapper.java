package org.jcr.architectureportsandadapters.application.mapper;

import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.port.in.web.dto.request.UserRequest;
import org.jcr.architectureportsandadapters.port.in.web.dto.response.UserResponse;
import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * Mapper para convertir entre entidades, DTOs y objetos de dominio.
 * Utiliza MapStruct para generar implementaciones automáticamente.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Convierte un objeto de dominio User a UserEntity.
     */
    UserEntity toEntity(User userDomain);
    
    /**
     * Convierte un UserEntity a objeto de dominio User.
     */
    User toUserDomain(UserEntity userDomain);
    
    /**
     * Convierte un UserRequest a objeto de dominio User.
     */
    User toUserDomain(UserRequest userRequest);
    
    /**
     * Convierte un objeto de dominio User a UserResponse.
     */
    UserResponse toResponse(User userDomain);
}
