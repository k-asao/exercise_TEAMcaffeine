package jp.co.froide.exercise.TeamCoffein.entity;

import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Table(name = "employee")
@Entity(metamodel = @Metamodel)
@Getter
@Setter
public class PostEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer emp_id;
    String name;
    String kana;
    String hire_date;
    Integer post_id;
    Integer dept_id;
    String tel;
    String email;
    String password;
    Integer delete_flag;
    String update_at;
    String create_at;

    public void cloneForm(EmployeeForm form){
        this.name = form.getName();
        this.kana = form.getKana();
        this.hire_date = form.getHire_date();
        this.post_id = form.getPost_id();
        this.dept_id = form.getDept_id();
        this.tel = form.getTel();
        this.email = form.getEmail();
    }
}
