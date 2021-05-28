package jp.co.froide.exercise.TeamCoffein.validation;

import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmpFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
//        EmployeeForm empForm = (EmployeeForm) target;
//        if (!EmployeeForm.password.equals(EmployeeForm.passwordConfirm)) {
//            errors.rejectValue("passwordConfirm", "UserFormValidator.userForm.passwordConfirm","これがエラ〜メッセージですよ。");
//        }
    }
}

