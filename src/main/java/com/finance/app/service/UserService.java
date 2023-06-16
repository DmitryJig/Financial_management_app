package com.finance.app.service;

import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.entity.User;
import com.finance.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(String.format("User username: %s not found", username)));
    }
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User with id = %d not found", id)));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void createUser(User user){
        user.setRoles(List.of(roleService.getEntityRole()));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
