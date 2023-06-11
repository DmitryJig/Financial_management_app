package com.finance.app.service;

import com.finance.app.model.entity.Role;
import com.finance.app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getEntityRole(){
        return roleRepository.findByRoleName("ROLE_EMPLOYEE").get();
    }
}
