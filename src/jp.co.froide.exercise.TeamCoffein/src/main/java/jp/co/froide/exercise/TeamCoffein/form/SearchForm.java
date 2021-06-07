package jp.co.froide.exercise.TeamCoffein.form;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class SearchForm implements Serializable {
    Integer emp_id;
    String order;
    String name;
    Integer post_id;
    Integer dept_id;
    String hire_date;
    Integer lim;
    Integer off;
}
