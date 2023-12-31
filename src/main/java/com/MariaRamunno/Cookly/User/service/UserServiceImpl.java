package com.MariaRamunno.Cookly.User.service;

import com.MariaRamunno.Cookly.User.exceptions.UserAlreadyExistsException;
import com.MariaRamunno.Cookly.User.exceptions.UserNotFoundException;
import com.MariaRamunno.Cookly.User.model.User;
import com.MariaRamunno.Cookly.User.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User createUser(User user) {
        if (userAlreadyExists(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail() + " already exists!");
        }
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserbyId(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No User found with this id: " + id));
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("Sorry, this user could not be found."));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepo.existsById(id)){
            throw new UserNotFoundException("Sorry, user not found.");
        }
        userRepo.deleteById(id);
    }

    private boolean userAlreadyExists(String email) {
        return userRepo.findByEmail(email).isPresent();
    }
}