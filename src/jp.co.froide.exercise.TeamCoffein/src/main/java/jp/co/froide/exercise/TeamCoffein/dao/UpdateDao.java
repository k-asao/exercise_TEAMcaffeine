package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

//更新・削除用Dao
@ConfigAutowireable
@Dao
public interface UpdateDao {

    @Sql("select /*%expand*/* from employee where emp_id = /*id*/0")
    @Select
    Employee selectEmpByID(Integer id);


    @Update
    int update(Employee employee);

    @Delete
    int delete(Employee employee);
}
