package services;

import forms.LoginForm;
import forms.SignUpForm;
import models.*;

import org.json.JSONArray;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    boolean signUp(SignUpForm userForm);
    Optional<User> signIn(User user);
    boolean signUp(User user);
    Optional<User> signIn(LoginForm loginForm);
    List<Card> getUserCard(User user);
    List<BankAccount> getBankAccount(User user);
    List<Transaction> getUserTransaction(User user);
    List<Balance> getUserBalances(User user);
    int getPercentFromSalary(List<Card> cards, List<BankAccount> bankAccounts);
    List<Balance> check(JSONArray jsonValues, User user);
    void saveUUid(UUIDUser uuidUser);
}
