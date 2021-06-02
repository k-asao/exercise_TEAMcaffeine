package jp.co.froide.exercise.TeamCoffein.form;
import lombok.Data;

import java.io.Serializable;

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
