package com.finance.app.converters;

import com.finance.app.model.dto.UserDto;
import com.finance.app.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * Не стал делать лишних методов так как пока неизвестно в каких полях будет потребность
 */
@Component
public class UserConverter {

    public UserDto entityToDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
