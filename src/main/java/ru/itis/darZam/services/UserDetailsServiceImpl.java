package ru.itis.darZam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.User;
import ru.itis.darZam.models.UserDetailsImpl;
import ru.itis.darZam.repository.UserRepository;

import java.util.Optional;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(s);
        if (userOptional.isPresent()){
            return new UserDetailsImpl(userOptional.get());
        }else {
            throw new SecurityException("User with email <" + s + "> not found");
        }
    }
}
