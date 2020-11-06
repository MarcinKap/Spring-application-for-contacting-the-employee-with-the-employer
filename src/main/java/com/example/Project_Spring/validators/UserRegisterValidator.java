package com.example.Project_Spring.validators;



import com.example.Project_Spring.security.LoginUser;
import com.example.Project_Spring.security.UserApp;
import com.example.Project_Spring.utilities.AppUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class UserRegisterValidator implements Validator {


    @Override
    public boolean supports(Class<?> cls) {
        return UserApp.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        LoginUser u = (LoginUser) obj;

        ValidationUtils.rejectIfEmpty(errors, "title", "error.userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.userLastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.userPassword.empty");

        if (!u.getEmail().equals(null)) {
            boolean isMatch = AppUtils.checkEmail(u.getEmail());
            if (!isMatch) {
                errors.rejectValue("email", "error.userEmailIsNotMatch");
            }
        }
        if (!u.getPassword().equals(null)) {
            boolean isMatch = AppUtils.checkPassword(u.getPassword());
            if (!isMatch) {
                errors.rejectValue("password", "error.userPasswordIsNotMatch");
            }
        }


    }


    public void validateEmailExist(UserApp userApp, Errors errors) {

        if (userApp != null) {
            errors.rejectValue("email", "error.userEmailExist");
        }

    }



}

