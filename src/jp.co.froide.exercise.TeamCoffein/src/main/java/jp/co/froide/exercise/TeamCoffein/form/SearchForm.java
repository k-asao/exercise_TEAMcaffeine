package jp.co.froide.exercise.TeamCoffein.form;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class SearchForm implements Serializable {
    String order;
    String name;
    Integer post_id;
    Integer dept_id;
    String hire_date;

    public SearchForm(String order, String name, Integer post_id, Integer dept_id, String hire_date){
        this.order = order;
        this.name = name;
        this.post_id = post_id;
        this.dept_id = dept_id;
        this.hire_date = hire_date;
    }
    public SearchForm(){}
}
