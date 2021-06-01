
package jp.co.froide.exercise.TeamCoffein.validation;
import jp.co.froide.exercise.TeamCoffein.form.ConfPassForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PassFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ConfPassForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ConfPassForm passForm = (ConfPassForm)  target;
        if (!passForm.getPass().equals(passForm.getPassConf())) {
            errors.rejectValue("passConf", "UserFormValidator.userForm.passwordConfirm");
            System.out.println("ppppp");
        }

    }
}
