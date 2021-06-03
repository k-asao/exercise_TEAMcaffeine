package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

//所属部署用Dao
@ConfigAutowireable
@Dao
public interface RemovedEmployeeDao {
    @Sql("select /*%expand*/*  from employee inner join department on employee" +
            ".dept_id = department.dept_id and employee.delete_flag = 1 inner join post on employee.post_id = post.post_id order by emp_id asc")
    @Select
    List<Employee> selectRemovedDataAll();

}
