package org.jcr.architectureportsandadapters.port.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.application.mapper.UserMapper;
import org.jcr.architectureportsandadapters.port.in.CreateUserPort;
import org.jcr.architectureportsandadapters.port.in.GetUserPort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.port.in.web.dto.request.UserRequest;
import org.jcr.architectureportsandadapters.port.in.web.dto.response.UserResponse;
import org.jcr.architectureportsandadapters.shared.response.ApiResponse;
import org.jcr.architectureportsandadapters.shared.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar operaciones relacionadas con usuarios.
 * 
 * CONEXIONES ARQUITECTÓNICAS:
 * 
 * 1. ADAPTADOR PRIMARIO (Primary Adapter):
 *    - Este controlador es un ADAPTADOR PRIMARIO que traduce las peticiones HTTP
 *      en llamadas a los puertos de entrada del dominio.
 * 
 * 2. INYECCIÓN DE PUERTOS DE ENTRADA:
 *    - createUserPort: Puerto de entrada para crear usuarios
 *    - getUserPort: Puerto de entrada para consultar usuarios
 *    - Estos puertos son INTERFACES que definen los casos de uso disponibles
 *    - Spring inyecta automáticamente las implementaciones (UserService)
 * 
 * 3. MAPPER PARA TRANSFORMACIONES:
 *    - userMapper: Convierte entre DTOs (UserRequest/UserResponse) y objetos de dominio (User)
 *    - Mantiene la separación entre la representación HTTP y el modelo de dominio
 * 
 * 4. FLUJO DE DATOS:
 *    HTTP Request -> DTO -> Dominio -> Puerto de Entrada -> Servicio de Aplicación
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // PUERTOS DE ENTRADA: Interfaces que definen los casos de uso
    // Spring inyectará automáticamente UserService que implementa estos puertos
    private final CreateUserPort createUserPort;  // Caso de uso: Crear usuario
    private final GetUserPort getUserPort;        // Caso de uso: Consultar usuario
    
    // MAPPER: Transformador entre capas
    // Convierte DTOs ↔ Objetos de Dominio ↔ Entidades JPA
    private final UserMapper userMapper;

    /**
     * Endpoint para crear un nuevo usuario.
     * 
     * FLUJO DE CONEXIÓN ENTRE CAPAS:
     * 1. HTTP Layer -> Controller (Adaptador Primario)
     * 2. Controller -> Mapper (Transformación DTO -> Dominio)
     * 3. Controller -> Puerto de Entrada (createUserPort)
     * 4. Puerto -> Servicio de Aplicación (UserService)
     * 5. Servicio -> Puerto de Salida (UserPersistencePort)
     * 6. Puerto de Salida -> Adaptador Secundario (UserJpaAdapter)
     * 7. Adaptador -> Repositorio JPA -> Base de Datos
     * 8. Respuesta: BD -> JPA -> Adaptador -> Puerto -> Servicio -> Controller -> HTTP
     *
     * @param userRequest DTO con datos del usuario a crear.
     * @return Respuesta con datos del usuario creado.
     */
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        // PASO 1: Transformación de DTO a Objeto de Dominio
        // UserRequest (HTTP/JSON) -> User (Dominio)
        // El mapper mantiene la separación entre capas
        final User userDomain = userMapper.toUserDomain(userRequest);
        
        // PASO 2: Llamada al Puerto de Entrada
        // El controlador NO conoce la implementación, solo la interfaz
        // Spring inyecta UserService que implementa CreateUserPort
        final User createdUser = createUserPort.createUser(userDomain);

        // PASO 3: Transformación de Objeto de Dominio a DTO de Respuesta
        // User (Dominio) -> UserResponse (HTTP/JSON)
        final UserResponse response = userMapper.toResponse(createdUser);

        // PASO 4: Respuesta HTTP estandarizada
        return ApiResponse.success(response, "Usuario creado exitosamente");
    }

    /**
     * Endpoint para obtener información de un usuario por ID.
     * 
     * FLUJO DE CONEXIÓN PARA CONSULTA:
     * 1. HTTP GET /api/v1/users/{id} -> Controller
     * 2. Controller -> Puerto de Entrada (getUserPort.findById)
     * 3. Puerto -> UserService.findById()
     * 4. UserService -> Puerto de Salida (userPersistencePort.findById)
     * 5. Puerto de Salida -> UserJpaAdapter.findById()
     * 6. Adapter -> SpringDataUserRepository.findById() -> BD
     * 7. BD -> UserEntity -> Mapper -> User (Dominio) -> Controller
     * 8. Controller -> Mapper -> UserResponse -> HTTP JSON
     *
     * @param id ID del usuario.
     * @return Respuesta con datos del usuario.
     */
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long id) {
        // PASO 1: Llamada al Puerto de Entrada para consulta
        // El Optional permite manejar casos donde el usuario no existe
        // Si no se encuentra, lanza UserNotFoundException (manejo global de errores)
        final User user = getUserPort.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        
        // PASO 2: Transformación de Dominio a DTO de respuesta
        final UserResponse response = userMapper.toResponse(user);
        
        // PASO 3: Respuesta HTTP con formato estandarizado
        return ApiResponse.success(response, "Usuario encontrado");
    }
}
