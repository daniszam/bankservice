package services;

import forms.LoginForm;
import forms.SignUpForm;
import models.User;

import java.util.Optional;

public interface UsersService {
    boolean signUp(SignUpForm userForm);
    boolean signIn(User user);
    boolean signUp(User user);
    Optional<User> signIn(LoginForm loginForm);
}
