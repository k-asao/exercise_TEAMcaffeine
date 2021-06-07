package jp.co.froide.exercise.TeamCoffein.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

//内部結合後の履歴受取用
@Table(name = "emp_history")
@Entity(metamodel = @Metamodel)
@Getter
@Setter
public class EmpHisReceive {

    Integer emp_id;
    String name;
    String kana;
    String hire_date;
    String post_name;
    String dept_name;
    String tel;
    String email;
    String insert_history_at;
}
