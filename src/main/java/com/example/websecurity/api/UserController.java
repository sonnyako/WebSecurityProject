package com.example.websecurity.api;

import com.example.websecurity.dao.model.RoleName;
import com.example.websecurity.dao.model.User;
import com.example.websecurity.dto.UserDto;
import com.example.websecurity.mapper.UserMapper;
import com.example.websecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class UserController {
    private final UserService userService;

    @GetMapping("/get/by/username")
    @PreAuthorize("hasAnyAuthority('ROLE_FREELANCER', 'ROLE_OWNER', 'ADMIN')")
    public UserDto getUserProfile(@RequestParam(name = "username") String username) {
        return UserMapper.INSTANCE.toDto(userService.findByUsername(username));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_FREELANCER', 'ROLE_OWNER', 'ADMIN')")
    @GetMapping("/get/all")
    public List<UserDto> getAll() {
        return userService.findAll().stream()
                .map(UserMapper.INSTANCE::toDto)
                .toList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_FREELANCER', 'ROLE_OWNER', 'ADMIN')")
    @GetMapping("/get/by/role")
    public List<UserDto> getUserByRole(@RequestParam(name = "roleName") RoleName roleName) {
        return userService.findAllByRoles(roleName).stream()
                .map(UserMapper.INSTANCE::toDto)
                .toList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_FREELANCER', 'ROLE_OWNER', 'ADMIN')")
    @GetMapping("/get/byId/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        return UserMapper.INSTANCE.toDto(user);
    }

}
