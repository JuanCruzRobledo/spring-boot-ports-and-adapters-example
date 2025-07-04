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
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserPort createUserPort;
    private final GetUserPort getUserPort;
    private final UserMapper userMapper;

    /**
     * Endpoint para crear un nuevo usuario.
     *
     * @param userRequest DTO con datos del usuario a crear.
     * @return Respuesta con datos del usuario creado.
     */
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        final User userDomain = userMapper.toUserDomain(userRequest);
        final User createdUser = createUserPort.createUser(userDomain);

        final UserResponse response = userMapper.toResponse(createdUser);

        return ApiResponse.success(response, "Usuario creado exitosamente");
    }

    /**
     * Endpoint para obtener información de un usuario por ID.
     *
     * @param id ID del usuario.
     * @return Respuesta con datos del usuario.
     */
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long id) {
        final User user = getUserPort.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        final UserResponse response = userMapper.toResponse(user);
        return ApiResponse.success(response, "Usuario encontrado");
    }
}
