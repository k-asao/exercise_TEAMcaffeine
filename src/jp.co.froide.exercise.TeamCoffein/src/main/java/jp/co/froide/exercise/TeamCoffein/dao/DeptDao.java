package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Department;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

//所属部署用Dao
@ConfigAutowireable
@Dao
public interface DeptDao {
    @Sql("select /*%expand*/* from department")
    @Select
    List<Department> selectAll();
}
