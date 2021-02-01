package com.shopflix.storefront.forms.validation;

import com.shopflix.storefront.forms.RegisterForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value = "registrationValidator")
public class RegistrationValidator implements Validator {

    public static final Pattern EMAIL_REGEX = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
    public static final Pattern PWD_REGEX = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$");

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterForm.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        final RegisterForm registerForm = (RegisterForm) object;
        final String username = registerForm.getUsername();
        final String uid = registerForm.getUid();
        final String pwd = registerForm.getPwd();
        final String checkPwd = registerForm.getCheckPwd();
        final boolean termsCheck = registerForm.isTermsCheck();

        validateUserName(errors, username);
        validateUid(errors, uid);
        validatePassword(errors, pwd);
        comparePasswords(errors, pwd, checkPwd);
        validateTermsAndConditions(errors, termsCheck);
    }


    protected void validateUserName(final Errors errors, final String username) {
        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("username", "register.username.invalid");
        } else if (StringUtils.length(username) > 255) {
            errors.rejectValue("username", "register.username.invalid");
        }
    }

    protected void validateUid (final Errors errors, final String uid) {
        if (StringUtils.isEmpty(uid)) {
            errors.rejectValue("email", "register.email.invalid");
        } else if (StringUtils.length(uid) > 255 || !validateEmailAddress(uid)) {
            errors.rejectValue("email", "register.email.invalid");
        }
    }

    public boolean validateEmailAddress(final String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }


    protected void validatePassword(final Errors errors, final String pwd) {
        if (StringUtils.isEmpty(pwd)) {
            errors.rejectValue("pwd", "register.pwd.invalid");
        } else if (StringUtils.length(pwd) < 8 || StringUtils.length(pwd) > 255 || !validatePasswordComplexity(pwd)) {
            errors.rejectValue("pwd", "register.pwd.invalid");
        }
    }

    public boolean validatePasswordComplexity(final String pwd) {
        // match minimum eight characters, at least one letter and one number:
        Matcher matcher = PWD_REGEX.matcher(pwd);
        return matcher.matches();
    }

    protected void comparePasswords(final Errors errors, final String pwd, final String checkPwd) {
        if (StringUtils.isNotEmpty(pwd) && StringUtils.isNotEmpty(checkPwd) && !StringUtils.equals(pwd, checkPwd)) {
            errors.rejectValue("checkPwd", "validation.checkPwd.equals");
        } else {
            if (StringUtils.isEmpty(checkPwd)) {
                errors.rejectValue("checkPwd", "register.checkPwd.invalid");
            }
        }
    }

    protected void validateTermsAndConditions(final Errors errors, final boolean termsCheck)
    {
        if (!termsCheck)
        {
            errors.rejectValue("termsCheck", "register.terms.not.accepted");
        }
    }
}
