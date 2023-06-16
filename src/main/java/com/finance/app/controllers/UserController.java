package com.finance.app.controllers;

import com.finance.app.converters.UserConverter;
import com.finance.app.exception.AppException;
import com.finance.app.model.dto.RegistrationUserDto;
import com.finance.app.model.dto.UserDto;
import com.finance.app.model.entity.User;
import com.finance.app.service.JwtService;
import com.finance.app.service.UserDetailServiceImpl;
import com.finance.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
//@Tag(name = "User controller", description = "Контроллер для сущности User")
public class UserController {
    private final UserDetailServiceImpl userDetailService;
    private final UserService userService;
    private final UserConverter userConverter;
    private final JwtService jwtService;

    @GetMapping
    public List<UserDto> findAll(){
        return userService.findAll().stream().map(userConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id){
        return userConverter.entityToDto(userService.findById(id));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser (@RequestBody RegistrationUserDto registrationUserDto){
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppException(HttpStatus.BAD_REQUEST.value(), "Подтверждение пароля и пароль не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUsername(registrationUserDto.getUsername())){
            return new ResponseEntity<>(new AppException(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userConverter.dtoToEntity(registrationUserDto);
        userService.createUser(user);

        UserDetails userDetails = userDetailService.loadUserByUsername(registrationUserDto.getUsername());
        String token = jwtService.getToken(userDetails);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
    }
}
