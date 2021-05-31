package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;

import java.util.List;

@ConfigAutowireable
@Dao
public interface UserDao {
    default int count() {
        Config config = Config.get(this);
        SelectBuilder builder = SelectBuilder.newInstance(config);
        builder.sql("select count(*) from employee");
        return builder.getScalarSingleResult(int.class);
    }

    @Sql("select /*%expand*/*  from employee inner join department on employee" +
            ".dept_id = department.dept_id inner join post on employee.post_id = post.post_id order by emp_id asc")
    @Select
    List<Employee> selectEmpAll();

    @Sql("select /*%expand*/* from employee inner join department on employee.dept_id = department.dept_id " +
            "inner join post on employee.post_id = post.post_id where /*%if name != \"\" */ " +
            "name like /* name */99 /*%end*/" +
            "/*%if hire_date != null */ and hire_date = /* hire_date */99 /*%end*/" +
            "/*%if post_id != null */ and employee.post_id = /* post_id */99 /*%end*/" +
            "/*%if dept_id != null */ and employee.dept_id = /* dept_id */99 /*%end*/" +
            "order by  emp_id /*# order */")
    @Select
    List<Employee> selectSearchAll(String order, String name, Integer post_id, Integer dept_id, String hire_date);

    @Sql("select /*%expand*/* from employee where id = /* id */0")
    @Select
    Employee selectById(Integer id);

    @Sql("select /*%expand*/* from department")
    @Select
    List<Department> selectDeptAll();

    @Sql("select /*%expand*/* from post")
    @Select
    List<Post> selectPostAll();


    @Insert
    int insert(Employee emp);

    @Update
    int update(Employee emp);

    @Delete
    int delete(Employee emp);

}
