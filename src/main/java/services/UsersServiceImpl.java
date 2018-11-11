package services;

import forms.LoginForm;
import forms.SignUpForm;
import models.Balance;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.BankUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersServiceImpl implements UsersService {

    private BankUserRepository bankUserRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl (BankUserRepository bankUserRepository){
        this.bankUserRepository = bankUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean signUp(SignUpForm userForm) {
        if(userForm.getPassword().length()>1){
            Pattern emailPat = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
            Matcher matcher = emailPat.matcher(userForm.getEmail());
            if(matcher.find() ){
                User user = User.builder()
                        .email(userForm.getEmail())
                        .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                        .birthday(userForm.getBirthday())
                        .build();
                bankUserRepository.save(user);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
//        short g = 1;
//        User user = User.builder()
//                //.firstName(userForm.getFirstName())
//                //.lastName(userForm.getLastName())
//                .email(userForm.getEmail())
//               // .gender(g)
//                .birthday(userForm.getBirthday())
//                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
//                .build();
//        bankUserRepository.save(user);
    }

    public boolean signIn(User user){
        Optional<User> optionalUser = bankUserRepository.findOneByEmail(user.getEmail());
        if(!optionalUser.isPresent()){
            return false;
        }

        user.setId(optionalUser.get().getId());
        user.setHashPassword(optionalUser.get().getHashPassword());
        user.setBalances(optionalUser.get().getBalances());
        user.setTransactions(optionalUser.get().getTransactions());
        return true;
    }



    @Override
    public Optional<User> signIn(LoginForm loginForm) {
     //   System.out.println(loginForm);
        Optional<User> optionalUser = bankUserRepository.findOneByEmail(loginForm.getEmail());
      //  System.out.println(optionalUser);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(!passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())){
               // throw new IllegalArgumentException("Wrong password or email");
                return Optional.empty();
            }
            return Optional.of(user);
        }else{
            throw new IllegalArgumentException("Wrong password or email");
        }
    }
}
