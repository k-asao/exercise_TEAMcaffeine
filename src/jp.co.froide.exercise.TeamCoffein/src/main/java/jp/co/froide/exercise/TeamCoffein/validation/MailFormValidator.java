package jp.co.froide.exercise.TeamCoffein.validation;
import jp.co.froide.exercise.TeamCoffein.dao.EmployeeDao;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.form.ConfMailForm;
import jp.co.froide.exercise.TeamCoffein.form.ConfPassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MailFormValidator implements Validator {
    @Autowired
    EmployeeDao empDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return ConfMailForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ConfMailForm mailForm = (ConfMailForm)  target;
        PostEmployee emp = empDao.selectByEmail(mailForm.getEmail());
        if(!mailForm.getEmail().equals("") && emp == null){
            errors.rejectValue("email", "MailFormValidator.email.notFound");
        }else if(emp != null && emp.getPassword().equals("0")){
            errors.rejectValue("email", "MailFormValidator.email.notAdmin");
        }

    }
}
