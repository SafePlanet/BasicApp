package com.spi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spi.dm.AppUserForm;
import com.spi.dm.User;
import com.spi.repository.UserRepository;
  
@Component
public class AppUserValidator implements Validator {
  
    // common-validator library.
	@Autowired
    private EmailValidator emailValidator;
  
    @Autowired
    private UserRepository appUserDAO;
  
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }
  
    @Override
    public void validate(Object target, Errors errors) {
         
        AppUserForm form = (AppUserForm) target;
           
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "", "User name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");
  
        if (errors.hasErrors()) {
            return;
        }
  
        if (!emailValidator.validate(form.getEmail())) {
               
            errors.rejectValue("email", "", "Email is not valid");
            return;
        }
  
        User userAccount = appUserDAO.findByUsername(form.getUserName());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getId() )) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            }
        }
  
        userAccount = appUserDAO.findByEmailAddress(form.getEmail());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getId() )) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            }
        }
    }
  
}
