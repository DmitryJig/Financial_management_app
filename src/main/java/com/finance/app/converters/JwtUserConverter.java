package com.finance.app.converters;

import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.User;
import com.finance.app.security.jwt.JwtUser;
import com.finance.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUserConverter {
    private final UserService userService;

    public JwtUser userToJwtUser(UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return new JwtUser(
                user.getId(),
                user.getProfiles().stream().map(Profile::getId).collect(Collectors.toList()),
                user.getUsername(),
                user.getPassword(),
                userDetails.getAuthorities()
        );
    }
}
