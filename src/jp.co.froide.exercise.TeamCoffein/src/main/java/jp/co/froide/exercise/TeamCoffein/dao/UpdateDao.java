package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.NewEmp;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface UpdateDao {

    @Sql("select /*%expand*/* from employee where emp_id = /*id*/0")
    @Select
    Employee selectEmpByID(Integer id);


    @Sql("select /*%expand*/* from employee inner join post on post.post_id=employee.post_id " +
            "inner join department on department.dept_id=employee.dept_id")
    @Select
    List<NewEmp> selectALLEmp();


    @Update
    int update(Employee employee);

    @Delete
    int delete(Employee employee);
}
