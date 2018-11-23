package services;

import forms.LoginForm;
import forms.SignUpForm;
import lombok.SneakyThrows;
import models.Balance;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.BankAccountRepository;
import repositories.BankUserRepository;
import repositories.CardRepository;

import javax.sql.DataSource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersServiceImpl implements UsersService {

    private BankUserRepository bankUserRepository;
    private PasswordEncoder passwordEncoder;
    private CardRepository cardRepository;
    private BankAccountRepository bankAccountRepository;

    public UsersServiceImpl(DataSource dataSource) {
        this.bankUserRepository = new BankUserRepository(dataSource);
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.cardRepository = new CardRepository(dataSource);
        this.bankAccountRepository = new BankAccountRepository(dataSource);
    }

    @Override
    @SneakyThrows
    public boolean signUp(SignUpForm userForm) {
        Date birthday = null;
        String birthdayStr = userForm.getBirthday();
        if (userForm != null && birthdayStr.length() > 8) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthday = new Date(simpleDateFormat.parse(birthdayStr).getTime());
        }
        if (userForm.getPassword().length() > 1) {
            Pattern emailPat = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
            Matcher matcher = emailPat.matcher(userForm.getEmail());
            if (matcher.find()) {
                User user = User.builder()
                        .firstName(userForm.getFirstName())
                        .lastName(userForm.getLastName())
                        .gender(userForm.getGender())
                        .email(userForm.getEmail())
                        .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                        .birthday(birthday)
                        .build();
                Optional<User> optionalUser = bankUserRepository.findOneByEmail(user.getEmail());
                if(!optionalUser.isPresent()) {
                    bankUserRepository.save(user);
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean signIn(User user) {
        Optional<User> optionalUser = bankUserRepository.findOneByEmail(user.getEmail());
        if (!optionalUser.isPresent()) {
            return false;
        }
        // user = optionalUser.get();
        List<Balance> balances = new ArrayList<>();
        balances.addAll(cardRepository.findAllByUserId(optionalUser.get().getId()));
        balances.addAll(bankAccountRepository.findAllByUserId(optionalUser.get().getId()));
        user.setBalances(balances);
        user.setId(optionalUser.get().getId());
        user.setHashPassword(optionalUser.get().getHashPassword());
        user.setTransactions(optionalUser.get().getTransactions());
        return true;
    }


    public boolean signUp(User user) {
        user.setHashPassword(passwordEncoder.encode(user.getHashPassword()));
        bankUserRepository.save(user);
        user.setBalances(new ArrayList<>());
        user.setTransactions(new ArrayList<>());
        user.setCredits(new ArrayList<>());
        return true;
    }


    @Override
    public Optional<User> signIn(LoginForm loginForm) {
        Optional<User> optionalUser = bankUserRepository.findOneByEmail(loginForm.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Balance> balances = new ArrayList<>();
            balances.addAll(cardRepository.findAllByUserId(optionalUser.get().getId()));
            balances.addAll(bankAccountRepository.findAllByUserId(optionalUser.get().getId()));
            user.setBalances(balances);
            if (!passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                return Optional.empty();
            }
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
