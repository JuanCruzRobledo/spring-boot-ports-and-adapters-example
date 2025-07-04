package org.jcr.architectureportsandadapters.port.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.jcr.architectureportsandadapters.application.mapper.UserMapper;
import org.jcr.architectureportsandadapters.port.in.CreateUserPort;
import org.jcr.architectureportsandadapters.port.in.GetUserPort;
import org.jcr.architectureportsandadapters.domain.model.User;
import org.jcr.architectureportsandadapters.port.in.web.dto.request.UserRequest;
import org.jcr.architectureportsandadapters.port.in.web.dto.response.UserResponse;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/users")
public class UserController {

    private final CreateUserPort createUserPort;
    private final GetUserPort getUserPort;
    private final UserMapper userMapper;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        final User userDomain = userMapper.toUserDomain(userRequest);
        final User createdUser = createUserPort.createUser(userDomain);

        final UserResponse response = userMapper.toResponse(createdUser);

        return response;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        final User user = getUserPort.findById(id).orElseThrow(()-> new IllegalArgumentException("User not found"));
        final UserResponse response = userMapper.toResponse(user);
        return response;
    }
}
