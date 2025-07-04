package org.jcr.architectureportsandadapters.application.service;

import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.port.in.CreateUserPort;
import org.jcr.architectureportsandadapters.port.in.GetUserPort;
import org.jcr.architectureportsandadapters.port.out.UserPersistencePort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio de aplicación para gestionar la lógica de negocio relacionada con los usuarios.
 * 
 * CONEXIONES ARQUITECTÓNICAS DEL SERVICIO DE APLICACIÓN:
 * 
 * 1. IMPLEMENTA PUERTOS DE ENTRADA (Input Ports):
 *    - CreateUserPort: Define el caso de uso para crear usuarios
 *    - GetUserPort: Define el caso de uso para consultar usuarios
 *    - Esto hace que UserService sea la IMPLEMENTACIÓN CONCRETA de estos contratos
 * 
 * 2. DEPENDE DE PUERTOS DE SALIDA (Output Ports):
 *    - UserPersistencePort: Interfaz para operaciones de persistencia
 *    - Spring inyectará automáticamente UserJpaAdapter que implementa este puerto
 * 
 * 3. POSICIÓN EN LA ARQUITECTURA:
 *    - Capa de APLICACIÓN: Orquesta casos de uso y coordina entre dominio e infraestructura
 *    - NO depende de tecnologías específicas (JPA, HTTP, etc.)
 *    - Solo depende de ABSTRACCIONES (interfaces/puertos)
 * 
 * 4. PATRÓN DE INYECCIÓN DE DEPENDENCIAS:
 *    Controller -> [CreateUserPort] -> UserService -> [UserPersistencePort] -> JpaAdapter
 *               ↑                                   ↑
 *         Depende de interfaz            Depende de interfaz
 */
@RequiredArgsConstructor
@Service
public class UserService implements CreateUserPort, GetUserPort {

    // PUERTO DE SALIDA: Interfaz hacia la capa de infraestructura
    // Spring inyectará UserJpaAdapter que implementa esta interfaz
    // PRINCIPIO DIP: Dependemos de abstracciones, no de implementaciones concretas
    private final UserPersistencePort userPersistencePort;

    /**
     * Crea un nuevo usuario en el sistema.
     * 
     * CONEXIÓN CON PUERTO DE SALIDA:
     * 1. Recibe un objeto User del dominio (desde el Controller via Puerto de Entrada)
     * 2. Delega la persistencia al Puerto de Salida (userPersistencePort)
     * 3. El puerto de salida es una INTERFAZ - no conocemos la implementación
     * 4. Spring inyecta UserJpaAdapter que implementa UserPersistencePort
     * 5. El adaptador se encarga de la transformación a UserEntity y persistencia JPA
     * 
     * FLUJO: Controller -> CreateUserPort -> UserService -> UserPersistencePort -> UserJpaAdapter
     *
     * @param user Objeto de dominio de usuario a crear.
     * @return Usuario creado.
     */
    @Override
    public User createUser(User user) {
        // Aquí se podrían agregar validaciones de negocio antes de persistir
        // Por ejemplo: validar duplicados, reglas de negocio, etc.
        
        // DELEGACIÓN AL PUERTO DE SALIDA:
        // No sabemos cómo se persiste (JPA, MongoDB, archivo, etc.)
        // Solo sabemos que existe un contrato (UserPersistencePort) que lo hará
        return userPersistencePort.save(user);
    }

    /**
     * Busca un usuario por su ID.
     * 
     * CONEXIÓN CON PUERTO DE SALIDA PARA CONSULTA:
     * 1. Recibe un ID desde el Controller (via Puerto de Entrada GetUserPort)
     * 2. Delega la consulta al Puerto de Salida (userPersistencePort.findById)
     * 3. El puerto devuelve Optional<User> - objeto de dominio, no entidad JPA
     * 4. UserJpaAdapter se encarga de:
     *    - Consultar SpringDataUserRepository.findById() -> Optional<UserEntity>
     *    - Transformar UserEntity -> User (objeto de dominio) usando UserMapper
     *    - Devolver Optional<User>
     * 
     * FLUJO: Controller -> GetUserPort -> UserService -> UserPersistencePort -> UserJpaAdapter -> JPA -> BD
     *
     * @param id ID del usuario a buscar.
     * @return Usuario encontrado, si existe.
     */
    @Override
    public Optional<User> findById(Long id) {
        // Aquí se podrían agregar validaciones adicionales:
        // - Log de auditoría
        // - Verificación de permisos
        // - Cache de consultas frecuentes
        
        // DELEGACIÓN AL PUERTO DE SALIDA:
        // El servicio no conoce la tecnología de persistencia subyacente
        return userPersistencePort.findById(id);
    }
}
