package services;

import forms.LoginForm;
import forms.SignUpForm;
import models.User;

import java.util.Optional;

public interface UsersService {
    boolean signUp(SignUpForm userForm);
    boolean signIn(User user);
    Optional<User> signIn(LoginForm loginForm);
}
