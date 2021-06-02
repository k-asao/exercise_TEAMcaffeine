package jp.co.froide.exercise.TeamCoffein.entity;

import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

import java.util.Optional;

@Table(name = "employee")
@Entity(metamodel = @Metamodel)
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer emp_id;
    String name;
    String kana;
    String hire_date;
    String post_name;
    String dept_name;
    String tel;
    String email;



}
