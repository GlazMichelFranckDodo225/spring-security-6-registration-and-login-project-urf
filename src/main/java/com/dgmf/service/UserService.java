package com.dgmf.service;

import com.dgmf.dto.UserDto;
import com.dgmf.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
