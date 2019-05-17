package ru.itis.darZam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.User;
import ru.itis.darZam.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void save(User user){
        userRepository.save(user);
    }

    public User getById(Long id){
        Optional<User> userOptional =  userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public void auth(User user){
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
    }
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
