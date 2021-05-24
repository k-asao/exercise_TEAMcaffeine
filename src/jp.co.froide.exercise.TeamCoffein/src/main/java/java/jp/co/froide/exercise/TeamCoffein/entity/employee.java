package java.jp.co.froide.exercise.TeamCoffein.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Table(name = "employee")
@Entity(metamodel = @Metamodel)
@Getter
@Setter
public class employee {

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


}
