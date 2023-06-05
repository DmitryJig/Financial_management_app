package com.finance.app.converters;

import com.finance.app.dto.UserDTO;
import com.finance.app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static User dtoToEntity(UserDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getRoles(), dto.getProfiles());
    }

    public static UserDTO entityToDTO(User entity) {
        return new UserDTO(entity.getId(), entity.getUsername(), null, entity.getEmail(), entity.getRoles(), entity.getProfiles());
    }
}
