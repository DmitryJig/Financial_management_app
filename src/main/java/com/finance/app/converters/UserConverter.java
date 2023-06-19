package com.finance.app.converters;

import com.finance.app.model.dto.RegUserDto;
import com.finance.app.model.dto.UserDto;
import com.finance.app.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Не стал делать лишних методов так как пока неизвестно в каких полях будет потребность
 */
@Component
@RequiredArgsConstructor
public class UserConverter {
    private final BCryptPasswordEncoder passwordEncoder;
    public UserDto entityToDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public User dtoToEntity(RegUserDto regUserDto){
        User user = new User();
        user.setUsername(regUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(regUserDto.getPassword()));
        user.setEmail(regUserDto.getEmail());
        user.setRoles(regUserDto.getRoles());
        return user;
    }
}
