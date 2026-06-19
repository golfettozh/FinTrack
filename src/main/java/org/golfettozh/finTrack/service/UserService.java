package org.golfettozh.finTrack.service;

import org.golfettozh.finTrack.entity.User;
import org.golfettozh.finTrack.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email, String cpf) {
        if (name == null || email == null || cpf == null || name.isBlank() || email.isBlank() || cpf.isBlank()) {
            throw new IllegalArgumentException("Name, Email, and CPF are mandatory and cannot be empty!");
        }

        User newUser = new User(name, email, cpf);

        return userRepository.save(newUser);
    }

    public List<User> returnAllUsers() {
        return userRepository.findAllUsers();
    }

    public User returnOneUserByID(Long user_id) {
        return userRepository.findUserById(user_id);
    }
}
