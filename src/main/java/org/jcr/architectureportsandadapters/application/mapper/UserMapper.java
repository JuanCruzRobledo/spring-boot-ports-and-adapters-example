package org.jcr.architectureportsandadapters.application.mapper;

import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.port.in.web.dto.request.UserRequest;
import org.jcr.architectureportsandadapters.port.in.web.dto.response.UserResponse;
import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User userDomain);
    User toUserDomain(UserEntity userDomain);
    User toUserDomain(UserRequest userRequest);
    UserResponse toResponse(User userDomain);
}
