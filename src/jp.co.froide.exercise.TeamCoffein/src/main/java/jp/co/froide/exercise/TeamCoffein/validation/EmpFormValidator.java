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
        Integer id = empForm.getEmp_id();
        if(empByEmail != null && !empByEmail.getEmp_id().equals(id)){
            errors.rejectValue("email", "EmployeeForm.Email.AlreadyExist");
        }
        if(empByTel != null && !empByTel.getEmp_id().equals(id)){
            errors.rejectValue("tel", "EmployeeForm.Tel.AlreadyExist");
        }
        if(id!=null) {
            PostEmployee empById = empDao.selectById(empForm.getEmp_id());
            int status = 0;
            if (!empById.getName().equals(empForm.getName())) {
                status = 1;
            } else if (!empById.getKana().equals(empForm.getKana())) {
                status = 1;
            } else if (!empById.getHire_date().equals(empForm.getHire_date())) {
                status = 1;
            } else if (!empById.getPost_id().equals(empForm.getPost_id())) {
                status = 1;
            } else if (!empById.getDept_id().equals(empForm.getDept_id())) {
                status = 1;
            } else if (!empById.getTel().equals(empForm.getTel())) {
                status = 1;
            } else if (!empById.getEmail().equals(empForm.getEmail())){
                status =1;
            }else if ( !(empById.getPassword().equals("0") && empForm.getAuth().intValue()==1) || (!empById.getPassword().equals("0") && empForm.getAuth().intValue()==0)) {
                status = 1;
            }

            if(status == 0){
                errors.rejectValue("emp_id", "EmployeeForm.notChange");
            }





        }
    }
}