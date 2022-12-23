package com.example.websecurity.service;

import com.example.websecurity.dao.model.RoleName;
import com.example.websecurity.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByUsername(String username);

    List<User> findAll();

    User findById(Long id);

    List<User> findAllByRoles(RoleName roleName);

    User save(User user);
}

