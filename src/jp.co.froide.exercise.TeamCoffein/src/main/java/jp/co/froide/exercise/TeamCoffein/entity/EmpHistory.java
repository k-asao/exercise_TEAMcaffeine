package jp.co.froide.exercise.TeamCoffein.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

//履歴登録用
@Table(name = "emp_history")
@Entity(metamodel = @Metamodel)
@Getter
@Setter
public class EmpHistory {


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
    String insert_history_at;

    public void cloneEmp(PostEmployee emp){
        this.emp_id = emp.getEmp_id();
        this.name = emp.getName();
        this.kana = emp.getKana();
        this.hire_date = emp.getHire_date();
        this.post_id = emp.getPost_id();
        this.dept_id = emp.getDept_id();
        this.tel = emp.getTel();
        this.email = emp.getEmail();
        this.password = emp.getPassword();
        this.delete_flag = emp.delete_flag;
        this.update_at = emp.getUpdate_at();
        this.create_at = emp.getCreate_at();
    }


}