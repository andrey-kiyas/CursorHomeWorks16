package com.CursorHomeWorks16.web;

import com.CursorHomeWorks16.dto.CreateUserDto;
import com.CursorHomeWorks16.dto.UserDto;
import com.CursorHomeWorks16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveUser(@Validated @RequestBody CreateUserDto user) {
        return userService.save(user);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserByUserId(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
}
