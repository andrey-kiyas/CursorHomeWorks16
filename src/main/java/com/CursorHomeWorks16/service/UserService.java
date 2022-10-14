package com.CursorHomeWorks16.service;

import com.CursorHomeWorks16.dto.CreateUserDto;
import com.CursorHomeWorks16.dto.UserDto;

import java.util.List;

public interface UserService {

    long save(CreateUserDto user);

    List<UserDto> getAllUsers();

    UserDto getById(Long id);

    void deleteAllUsers();

    Long deleteUserById(Long id);
}
