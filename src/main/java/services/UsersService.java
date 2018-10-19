package services;

import forms.LoginForm;
import forms.UserForm;

public interface UsersService {
    void signUp(UserForm userForm);

    void signIn(LoginForm loginForm);
}
