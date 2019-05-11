package ru.itis.darZam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.User;
import ru.itis.darZam.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public User getById(Long id){
        Optional<User> userOptional =  userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
