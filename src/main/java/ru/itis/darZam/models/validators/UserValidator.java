package ru.itis.darZam.models.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itis.darZam.models.User;

public class UserValidator implements Validator {

    private final Short MINIMUM_PASSWORD_LENGTH=6;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty" );

        User user = (User) o;

        if (user.getPassword() != null && user.getPassword().length() < MINIMUM_PASSWORD_LENGTH){
            errors.rejectValue("password", "field.min.length",
                    new Object[]{MINIMUM_PASSWORD_LENGTH},
                    "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
        }
    }
}
