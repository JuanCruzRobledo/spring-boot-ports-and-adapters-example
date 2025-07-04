package org.jcr.architectureportsandadapters.application.mapper;

import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.port.in.web.dto.request.UserRequest;
import org.jcr.architectureportsandadapters.port.in.web.dto.response.UserResponse;
import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * Mapper para convertir entre entidades, DTOs y objetos de dominio.
 * Utiliza MapStruct para generar implementaciones automáticamente.
 * 
 * CONEXIONES Y TRANSFORMACIONES:
 * 
 * 1. PAPEL EN LA ARQUITECTURA:
 *    - TRADUCTOR entre diferentes representaciones del mismo concepto (Usuario)
 *    - Mantiene SEPARACIÓN entre capas evitando dependencias directas
 *    - Permite que cada capa use su representación óptima
 * 
 * 2. TIPOS DE TRANSFORMACIONES:
 *    - HTTP ↔ Dominio: UserRequest/UserResponse ↔ User
 *    - Dominio ↔ Persistencia: User ↔ UserEntity
 * 
 * 3. USADO POR:
 *    - UserController: Para transformaciones HTTP ↔ Dominio
 *    - UserJpaAdapter: Para transformaciones Dominio ↔ JPA
 * 
 * 4. GENERACIÓN AUTOMÁTICA:
 *    - MapStruct genera implementación en tiempo de compilación
 *    - componentModel = "spring" hace que sea un bean de Spring
 *    - Spring lo inyecta donde se necesite
 * 
 * 5. VENTAJAS:
 *    - Sin código boilerplate manual
 *    - Type-safe en tiempo de compilación
 *    - Rendimiento óptimo (sin reflexión)
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Convierte un objeto de dominio User a UserEntity.
     * 
     * CONEXIÓN: Dominio → Persistencia
     * USADO EN: UserJpaAdapter.save() antes de persistir
     * FLUJO: UserService → UserJpaAdapter → UserMapper.toEntity() → JPA
     * 
     * TRANSFORMACIÓN:
     * - User (record inmutable) → UserEntity (clase mutable con anotaciones JPA)
     * - Mapea campos: id, nombre, apellido
     */
    UserEntity toEntity(User userDomain);
    
    /**
     * Convierte un UserEntity a objeto de dominio User.
     * 
     * CONEXIÓN: Persistencia → Dominio  
     * USADO EN: UserJpaAdapter.save() y UserJpaAdapter.findById()
     * FLUJO: JPA → UserEntity → UserMapper.toUserDomain() → UserService
     * 
     * TRANSFORMACIÓN:
     * - UserEntity (clase mutable) → User (record inmutable)
     * - Incluye ID generado por la base de datos
     */
    User toUserDomain(UserEntity userDomain);
    
    /**
     * Convierte un UserRequest a objeto de dominio User.
     * 
     * CONEXIÓN: HTTP → Dominio
     * USADO EN: UserController.createUser() al recibir petición
     * FLUJO: HTTP JSON → UserRequest → UserMapper.toUserDomain() → UserService
     * 
     * TRANSFORMACIÓN:
     * - UserRequest (sin ID) → User (con ID null para creación)
     * - Solo nombre y apellido desde el request HTTP
     */
    User toUserDomain(UserRequest userRequest);
    
    /**
     * Convierte un objeto de dominio User a UserResponse.
     * 
     * CONEXIÓN: Dominio → HTTP
     * USADO EN: UserController.createUser() y UserController.getUser()
     * FLUJO: UserService → User → UserMapper.toResponse() → HTTP JSON
     * 
     * TRANSFORMACIÓN:
     * - User (objeto de dominio) → UserResponse (DTO para HTTP)
     * - Incluye todos los campos: id, nombre, apellido
     */
    UserResponse toResponse(User userDomain);
}
