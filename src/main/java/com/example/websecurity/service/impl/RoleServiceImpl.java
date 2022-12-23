package com.example.websecurity.service.impl;

import com.example.websecurity.dao.model.Role;
import com.example.websecurity.dao.model.RoleName;
import com.example.websecurity.dao.repository.RoleRepository;
import com.example.websecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRole(String roleName) {
        return roleRepository.getByName(roleName);
    }
}
