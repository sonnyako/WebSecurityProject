package com.example.websecurity.security;

import com.example.websecurity.dao.model.User;
import com.example.websecurity.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUserDetailsService implements UserDetailsService {

    @Getter
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserService service;

    /**
     * {@inheritDoc}
     * todo: the user authorities not added yet›
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = service.findByUsername(username);

        return new JwtUserDetails(user);
    }

    /**
     * Locates the user based on the username.
     *
     * @param id the ID identifying the user whose data required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found.
     */
    public UserDetails loadUserById(Long id) {
        var user = service.findById(id);

        return new JwtUserDetails(user);
    }

    @Transactional
    public User save(User user) {
        var newUser = user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        return service.save(newUser);
    }
}
