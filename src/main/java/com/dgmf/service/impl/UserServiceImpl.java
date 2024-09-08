package com.dgmf.service.impl;

import com.dgmf.dto.UserDto;
import com.dgmf.entity.Role;
import com.dgmf.entity.User;
import com.dgmf.repository.RoleRepository;
import com.dgmf.repository.UserRepository;
import com.dgmf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        // Empty User Object
        User user = new User();

        // Convert UserDto to User Entity
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // Encrypt Password Using Spring Security PasswordEncoder
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        // If Role Does Not Exist
        if(role == null) {
            role = checkRoleExist();
        }

        // Set User Role
        user.setRoles(Arrays.asList(role));

        // Save User into DB
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");

        return roleRepository.save(role);
    }
}
