package org.jcr.architectureportsandadapters.infraestructure.persistence.repository.adapters;

import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.application.mapper.UserMapper;
import org.jcr.architectureportsandadapters.port.out.UserPersistencePort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.infraestructure.persistence.entity.UserEntity;
import org.jcr.architectureportsandadapters.infraestructure.persistence.repository.jpa.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adaptador JPA para persistencia de usuarios.
 * Implementa el puerto de salida UserPersistencePort.
 * 
 * CONEXIONES ARQUITECTÓNICAS DEL ADAPTADOR SECUNDARIO:
 * 
 * 1. IMPLEMENTA PUERTO DE SALIDA (Output Port):
 *    - UserPersistencePort: Contrato definido por la capa de aplicación
 *    - Este adaptador es la IMPLEMENTACIÓN CONCRETA para persistencia con JPA
 * 
 * 2. DEPENDE DE TECNOLOGÍAS ESPECÍFICAS:
 *    - SpringDataUserRepository: Repositorio JPA específico
 *    - UserMapper: Para transformaciones entre User (dominio) ↔ UserEntity (JPA)
 * 
 * 3. POSICIÓN EN LA ARQUITECTURA:
 *    - Capa de INFRAESTRUCTURA: Implementación concreta de tecnologías externas
 *    - ADAPTADOR SECUNDARIO: Permite al dominio interactuar con sistemas externos
 *    - Convierte llamadas del dominio en operaciones JPA específicas
 * 
 * 4. RESPONSABILIDADES:
 *    - Transformar objetos de dominio (User) a entidades JPA (UserEntity)
 *    - Ejecutar operaciones CRUD usando Spring Data JPA
 *    - Transformar resultados JPA de vuelta a objetos de dominio
 *    - Manejar excepciones específicas de persistencia
 * 
 * 5. FLUJO DE TRANSFORMACIÓN:
 *    UserService -> UserPersistencePort -> UserJpaAdapter -> UserMapper -> UserEntity -> JPA -> BD
 */
@RequiredArgsConstructor
@Repository
public class UserJpaAdapter implements UserPersistencePort {

    // REPOSITORIO JPA: Acceso directo a la base de datos usando Spring Data
    private final SpringDataUserRepository userRepository;
    
    // MAPPER: Transformador entre objetos de dominio y entidades JPA
    // Mantiene la separación entre el modelo de dominio y el modelo de persistencia
    private final UserMapper userMapper;

    /**
     * Guarda un usuario en la base de datos.
     * 
     * PROCESO DE TRANSFORMACIÓN Y PERSISTENCIA:
     * 1. Recibe User (objeto de dominio) desde UserService
     * 2. Transforma User -> UserEntity usando UserMapper.toEntity()
     * 3. Persiste UserEntity usando Spring Data JPA (userRepository.save())
     * 4. JPA devuelve UserEntity con ID generado
     * 5. Transforma UserEntity -> User usando UserMapper.toUserDomain()
     * 6. Devuelve User (objeto de dominio) al UserService
     * 
     * CONEXIONES:
     * UserService -> UserPersistencePort.save() -> UserJpaAdapter.save() -> UserMapper -> JPA -> BD
     * 
     * @param user Usuario a guardar.
     * @return Usuario guardado.
     */
    @Override
    public User save(User user) {
        // PASO 1: Transformación de Dominio a JPA
        // User (inmutable, dominio) -> UserEntity (mutable, persistencia)
        UserEntity userEntity = userMapper.toEntity(user);
        
        // PASO 2: Persistencia usando Spring Data JPA
        // El repositorio maneja la conexión a BD, transacciones, etc.
        userEntity = userRepository.save(userEntity);

        // PASO 3: Transformación de JPA a Dominio
        // UserEntity (con ID generado) -> User (objeto de dominio)
        User userDomain = userMapper.toUserDomain(userEntity);

        // PASO 4: Devolver objeto de dominio al UserService
        return userDomain;
    }

    /**
     * Busca un usuario por su ID.
     * 
     * PROCESO DE CONSULTA Y TRANSFORMACIÓN:
     * 1. Recibe ID desde UserService
     * 2. Consulta userRepository.findById(id) -> Optional<UserEntity>
     * 3. Si existe UserEntity, lo transforma a User usando UserMapper
     * 4. Devuelve Optional<User> (objeto de dominio) al UserService
     * 
     * CONEXIONES:
     * UserService -> UserPersistencePort.findById() -> UserJpaAdapter.findById() -> 
     * SpringDataUserRepository -> JPA -> BD -> UserEntity -> UserMapper -> User
     * 
     * MANEJO DE OPTIONAL:
     * - Si no existe en BD: Optional.empty()
     * - Si existe: Optional.of(User) tras transformación
     * 
     * @param id ID del usuario.
     * @return Usuario encontrado, si existe.
     */
    @Override
    public Optional<User> findById(Long id) {
        // FLUJO FUNCIONAL CON TRANSFORMACIÓN:
        // 1. userRepository.findById(id) -> Optional<UserEntity>
        // 2. .map(userMapper::toUserDomain) -> transforma UserEntity a User si existe
        // 3. Resultado: Optional<User> (objeto de dominio)
        
        return userRepository.findById(id)  // Optional<UserEntity>
                .map(userMapper::toUserDomain);  // Optional<User>
        
        // Equivalente a:
        // Optional<UserEntity> entityOpt = userRepository.findById(id);
        // if (entityOpt.isPresent()) {
        //     User user = userMapper.toUserDomain(entityOpt.get());
        //     return Optional.of(user);
        // }
        // return Optional.empty();
    }
}
