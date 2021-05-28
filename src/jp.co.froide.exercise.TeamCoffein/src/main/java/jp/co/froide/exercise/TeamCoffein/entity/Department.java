package jp.co.froide.exercise.TeamCoffein.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;


@Entity(metamodel = @Metamodel)
@Table(name = "department")
@Getter
@Setter
public class Department {

    @Id
    Integer dept_id;

    String dept_name;
}
