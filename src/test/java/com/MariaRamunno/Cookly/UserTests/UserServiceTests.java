package com.MariaRamunno.Cookly.UserTests;

import com.MariaRamunno.Cookly.Cookbook.exceptions.CookbookNotFoundException;
import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.User.exceptions.UserAlreadyExistsException;
import com.MariaRamunno.Cookly.User.exceptions.UserNotFoundException;
import com.MariaRamunno.Cookly.User.model.User;
import com.MariaRamunno.Cookly.User.repo.UserRepo;
import com.MariaRamunno.Cookly.User.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepo.findAll()).thenReturn(users);

        List<User> result = userService.getUsers();

        assertEquals(users,result);
    }

    @Test
    public void createUserSuccess() {
        User user = new User();
        when(userRepo.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
    }

    @Test
    public void updateUserSuccess() {
        long id = 1L;
        User existingUser = new User();
        existingUser.setId(id);
        User updatedUser = new User();
        updatedUser.setId(id);
        when(userRepo.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepo.save(existingUser)).thenReturn(updatedUser);

        User result = userService.updateUser(updatedUser);

        assertEquals(updatedUser, result);
    }
    @Test
    void updateUserNotFound() {
        long id = 1L;
        User updatedUser = new User();
        updatedUser.setId(id);
        when(userRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(updatedUser);
        });
    }

    @Test
    public void getUserByIdSuccess() {
        User user = new User();
        long id = 1L;
        when(userRepo.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getUserbyId(id);

        assertEquals(user, result);
    }

    @Test
    void getUserByIdNotFound() {
        long id = 1L;
        when(userRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserbyId(id);
        });
    }
}
