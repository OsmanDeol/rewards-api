package com.osman.rewards.service;

import com.osman.rewards.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(long id)
    {
        return userRepository.findById(id);
    }
    public User saveUser(User user)
    {
        user.setTier(calculateTier(user.getPoints()));
        return userRepository.save(user);
    }
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    private String calculateTier(int points) {
        if (points >= 5000) {
            return "Gold";
        } else if (points >= 1000) {
            return "Silver";
        } else {
            return "Bronze";
        }
    }

}
