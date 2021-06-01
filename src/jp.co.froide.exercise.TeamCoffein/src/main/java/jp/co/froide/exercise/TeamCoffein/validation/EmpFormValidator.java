package jp.co.froide.exercise.TeamCoffein.validation;


import jp.co.froide.exercise.TeamCoffein.dao.EmployeeDao;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmpFormValidator implements Validator {
    @Autowired
    EmployeeDao empDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        EmployeeForm empForm = (EmployeeForm) target;
        PostEmployee empByEmail = empDao.selectByEmail(empForm.getEmail());
        PostEmployee empByTel = empDao.selectByTel(empForm.getTel());

        if(empByEmail != null){
            errors.rejectValue("email", "EmployeeForm.Email.AlreadyExist");
        }
        if(empByTel != null){
            errors.rejectValue("tel", "EmployeeForm.Tel.AlreadyExist");
        }
    }
}