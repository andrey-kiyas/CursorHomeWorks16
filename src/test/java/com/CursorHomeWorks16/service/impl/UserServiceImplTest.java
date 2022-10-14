package com.CursorHomeWorks16.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.CursorHomeWorks16.dto.CreateUserDto;
import com.CursorHomeWorks16.dto.UserDto;
import com.CursorHomeWorks16.exceptions.UserNotFoundException;
import com.CursorHomeWorks16.model.User;
import com.CursorHomeWorks16.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.internal.util.collections.JoinedIterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testSave() {
        when(this.modelMapper.map((Object) any(), (Class<Object>) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setLastName("Doe");
        createUserDto.setAge(1);
        createUserDto.setFirstName("Jane");
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.save(createUserDto));
        verify(this.modelMapper).map((Object) any(), (Class<Object>) any());
    }

    @Test
    void testSave2() {
        User user = new User();
        user.setLastName("Doe");
        user.setId(123L);
        user.setAge(1);
        user.setFirstName("Jane");
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setId(123L);
        user1.setAge(0);
        user1.setFirstName("Jane");
        when(this.modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(user1);

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setLastName("Doe");
        createUserDto.setAge(1);
        createUserDto.setFirstName("Jane");
        assertEquals(123L, this.userServiceImpl.save(createUserDto));
        verify(this.userRepository).save((User) any());
        verify(this.modelMapper).map((Object) any(), (Class<Object>) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testGetAllUsers() {
        Iterable<User> iterable = (Iterable<User>) mock(Iterable.class);
        when(iterable.iterator()).thenReturn(new JoinedIterator<>(new ArrayList<>()));
        when(this.userRepository.findAll()).thenReturn(iterable);
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
        verify(this.userRepository).findAll();
        verify(iterable).iterator();
    }

    @Test
    void testGetById() {
        User user = new User();
        user.setLastName("Doe");
        user.setId(123L);
        user.setAge(1);
        user.setFirstName("Jane");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.modelMapper.map((Object) any(), (Class<Object>) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.getById(123L));
        verify(this.userRepository).findById((Long) any());
        verify(this.modelMapper).map((Object) any(), (Class<Object>) any());
    }

    @Test
    void testGetById2() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(this.modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.getById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testGetById3() {
        User user = new User();
        user.setLastName("Doe");
        user.setId(123L);
        user.setAge(1);
        user.setFirstName("Jane");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        UserDto userDto = new UserDto();
        userDto.setLastName("Doe");
        userDto.setAge(0);
        userDto.setFirstName("Jane");
        when(this.modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(userDto);
        assertSame(userDto, this.userServiceImpl.getById(123L));
        verify(this.userRepository).findById((Long) any());
        verify(this.modelMapper).map((Object) any(), (Class<Object>) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDeleteAllUsers() {
        doNothing().when(this.userRepository).deleteAll();
        this.userServiceImpl.deleteAllUsers();
        verify(this.userRepository).deleteAll();
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDeleteUserById() {
        doNothing().when(this.userRepository).deleteById((Long) any());
        assertEquals(123L, this.userServiceImpl.deleteUserById(123L).longValue());
        verify(this.userRepository).deleteById((Long) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }
}

