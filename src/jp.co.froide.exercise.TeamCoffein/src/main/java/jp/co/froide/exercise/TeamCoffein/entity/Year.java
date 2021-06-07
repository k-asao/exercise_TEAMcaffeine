package jp.co.froide.exercise.TeamCoffein.entity;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;

@Entity
@Table(name = "employee")
@Data
public class Year {
    String year;
}
