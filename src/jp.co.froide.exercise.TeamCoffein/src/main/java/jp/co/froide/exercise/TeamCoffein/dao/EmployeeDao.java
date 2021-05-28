package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface EmployeeDao {

    @Sql("select /*%expand*/* from employee where email = /*email*/0")
    @Select
    PostEmployee selectByEmail(String email);

}
