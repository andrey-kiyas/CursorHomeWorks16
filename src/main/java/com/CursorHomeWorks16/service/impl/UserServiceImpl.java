package com.CursorHomeWorks16.service.impl;

import com.CursorHomeWorks16.dto.CreateUserDto;
import com.CursorHomeWorks16.dto.UserDto;
import com.CursorHomeWorks16.exceptions.UserNotFoundException;
import com.CursorHomeWorks16.model.User;
import com.CursorHomeWorks16.repository.UserRepository;
import com.CursorHomeWorks16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public long save(CreateUserDto user) {
        return userRepository.save(modelMapper.map(user, User.class)).getId();
    }

    @Override
    public List<UserDto> getAllUsers() {
        ArrayList<User> arrayList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(arrayList::add);
        return arrayList
            .stream()
            .map(user -> modelMapper.map(user, UserDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserDto.class))
            .orElseThrow(() -> new UserNotFoundException("User with this id not found!"));
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public Long deleteUserById(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
