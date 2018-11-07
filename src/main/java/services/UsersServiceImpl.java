package services;

import forms.LoginForm;
import forms.UserForm;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.BankUserRepository;

import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    private BankUserRepository bankUserRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl (BankUserRepository bankUserRepository){
        this.bankUserRepository = bankUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(UserForm userForm) {
        short g = 1;
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .gender(g)
                .birthday(userForm.getBirthday())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .build();
        System.out.println(user);
        bankUserRepository.save(user);
    }

    @Override
    public void signIn(LoginForm loginForm) {
     //   System.out.println(loginForm);
        Optional<User> optionalUser = bankUserRepository.findOneByEmail(loginForm.getEmail());
      //  System.out.println(optionalUser);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(!passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())){
                throw new IllegalArgumentException("Wrong password or email");
            }
        }else{
            throw new IllegalArgumentException("Wrong password or email");
        }

    }
}
