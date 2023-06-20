package com.finance.app.controllers;

import com.finance.app.converters.UserConverter;
import com.finance.app.model.dto.RegUserDto;
import com.finance.app.model.dto.UserDto;
import com.finance.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{id}")
//@Tag(name = "User controller", description = "Контроллер для сущности User")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/all")
    public List<UserDto> findAll(){
        return userService.findAll().stream().map(userConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping
    public UserDto findById(@PathVariable Long id){
        return userConverter.entityToDto(userService.findById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser (@RequestBody RegUserDto regUserDto){
       return userService.createUser(regUserDto);
    }

    @DeleteMapping
    public void deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
    }
}
