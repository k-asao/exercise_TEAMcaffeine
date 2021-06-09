package jp.co.froide.exercise.TeamCoffein.form;

import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import lombok.Data;

import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class EmployeeForm implements Serializable {
    public static final long serialVersionUID= 1L;


    Integer emp_id;

    @Length(min=0, max=255)
    @NotBlank
    String name;

    @Length(min=0, max=255)
    @Pattern(regexp = "[ァ-タダ-ヶー・•]+", message = "全角カナで入力してください。")

    String kana;

    @NotBlank
    String hire_date;

    @NotNull
    Integer post_id;

    @NotNull
    Integer dept_id;


    @Pattern(regexp = "^0\\d{9,10}$", message = "半角数字で電話番号を入力してください。")
    String tel;

    @Length(min=0, max=255)
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "メールアドレスを入力してください。")
    @NotBlank
    String email;

    @NotNull
    Integer auth;

    public void cloneEmp(PostEmployee emp){
        this.emp_id = emp.getEmp_id();
        this.name = emp.getName();
        this.kana = emp.getKana();
        this.hire_date = emp.getHire_date();
        this.post_id = emp.getPost_id();
        this.dept_id = emp.getDept_id();
        this.tel = emp.getTel();
        this.email = emp.getEmail();
        if(emp.getPassword().equals("0")){
            this.auth = 1;
        }else{
            this.auth = 0;
        }

    }

}
