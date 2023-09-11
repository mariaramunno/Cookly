package com.MariaRamunno.Cookly.User.service;

import com.MariaRamunno.Cookly.User.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getUsers();
    User getUserbyId(Long id);
    User updateUser(User user, Long id);
    void deleteUser(Long id);
}
