package com.finance.app.converters;

import com.finance.app.model.dto.RegistrationUserDto;
import com.finance.app.model.dto.UserDto;
import com.finance.app.model.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Не стал делать лишних методов так как пока неизвестно в каких полях будет потребность
 */
@Component
public class UserConverter {
    BCryptPasswordEncoder passwordEncoder;
    public UserDto entityToDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public User dtoToEntity(RegistrationUserDto registrationUserDto){
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setEmail(registrationUserDto.getEmail());
        return user;
    }
}
