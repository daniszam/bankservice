package services;

import context.ApplicationDiContext;
import context.Contexts;
import forms.LoginForm;
import forms.SignUpForm;
import lombok.*;
import models.*;
import models.Transaction;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.*;
import utils.Circle;

import org.json.JSONArray;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private BankUserRepository bankUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UUIDRepository uuidRepository;

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
                if (!optionalUser.isPresent()) {
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

    public Optional<User> signIn(User user) {
        Optional<User> optionalUser = bankUserRepository.findOnlyUser(user);
        if (!optionalUser.isPresent()) {
            return Optional.empty();
        }
        return optionalUser;
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

    @Override
    public List<Card> getUserCard(User user) {
        return cardRepository.findAllByUserId(user.getId());
    }

    public List<Balance> getUserBalances(User user){
        if (user.getBalances() == null || user.getBalances().size() ==0 ) {
            List<Card> cards = getUserCard(user);
            List<BankAccount> bankAccounts = getBankAccount(user);
            List<Balance> balances = new ArrayList<>();
            balances.addAll(cards);
            balances.addAll(bankAccounts);
            user.setBalances(balances);
        }
        return user.getBalances();
    }

    @Override
    public int getPercentFromSalary(List<Card> cards, List<BankAccount> bankAccounts) {
        Circle circle = new Circle();
        return circle.getPercent(cards, bankAccounts);
    }

    @Override
    public List<Balance> check(JSONArray types, User user) {
        ApplicationDiContext applicationContext = Contexts.primitive();
        List<Balance> balances = new ArrayList<>();
        BalanceService balanceService = applicationContext.getComponent(BalanceService.class);
        for (int i = 0; i < types.length(); i++) {
            JSONObject type = types.getJSONObject(i);
            applicationContext.getComponent(BalanceService.class);
            Balance balance = this.getUserBalances(user).get(type.getInt("id"));
            balance.setBalance(balance.getBalance() + type.getInt("sum"));
            balanceService.increaseBalance(balance);
            balance.setUser(null);
            if (balance.getName() == null) {
                balance.setName(balance.getClass().getSimpleName());
            }
            balances.add(balance);
        }
        return balances;
    }


    @Override
    public List<BankAccount> getBankAccount(User user) {
        return bankAccountRepository.findAllByUserId(user.getId());
    }

    public List<Transaction> getUserTransaction(User user){
        if (user.getTransactions() == null){
            user.setTransactions(transactionRepository.findAllByUserId(user));
        }
        return user.getTransactions();
    }


    public void saveUUid(UUIDUser uuidUser){
        uuidRepository.save(uuidUser);
    }
}
