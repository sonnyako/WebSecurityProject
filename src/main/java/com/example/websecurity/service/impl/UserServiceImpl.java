package com.example.websecurity.service.impl;

import com.example.websecurity.dao.model.RoleName;
import com.example.websecurity.dao.model.User;
import com.example.websecurity.dao.repository.RoleRepository;
import com.example.websecurity.dao.repository.UserRepository;
import com.example.websecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public final class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public User findByUsername(String user) {
        return userRepository.findByUsername(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> findAllByRoles(RoleName roleName) {
        return userRepository.findAllByRole((long) roleName.ordinal() + 1);
    }

    @Override
    public User save(User user) {
        User userToSave = user.toBuilder()
                .role(roleRepository.getByName(user.getRole().getRoleName().toString()))
                .build();
        return userRepository.save(userToSave);
    }
}
