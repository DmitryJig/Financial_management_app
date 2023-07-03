package com.finance.app.controllers.impl;

import com.finance.app.controllers.UserController;
import com.finance.app.converters.UserConverter;
import com.finance.app.model.dto.RegUserDto;
import com.finance.app.model.dto.UserDto;
import com.finance.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @Override
    @GetMapping("/api/v1/users/{id}/all")
    public List<UserDto> findAll() {
        return userService.findAll()
                .stream()
                .map(userConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/api/v1/users/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userConverter.entityToDto(userService.findById(id));
    }

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody RegUserDto regUserDto) {
        return userService.createUser(regUserDto);
    }

    @Override
    @DeleteMapping("/api/v1/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}