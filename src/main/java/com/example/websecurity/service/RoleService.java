package com.example.websecurity.service;

import com.example.websecurity.dao.model.Role;
import com.example.websecurity.dao.model.RoleName;

public interface RoleService {

    Role getRole(String roleName);
}
