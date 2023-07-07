package com.example.auth.service;

import com.example.auth.entity.UserEntity;
import com.example.auth.entity.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@Primary
public class JPAUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    public JPAUserDetailsManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        createUser(User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .build());
    }


    @Override
    public void createUser(UserDetails user) {
        // 이미 있으면
        if(this.userExists(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        this.userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    // 실제로 Spring Security 내부에서 사용하는 반드시 구현해야 정상 동작을 할 수 있는 메소ㅒ
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser =
                userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new UsernameNotFoundException(username);
        UserEntity userEntity = optionalUser.get();
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }
}
