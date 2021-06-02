package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.EmpHisReceive;
import jp.co.froide.exercise.TeamCoffein.entity.EmpHistory;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;

import java.util.List;

//更新・削除用Dao
@ConfigAutowireable
@Dao
public interface UpdateDao {

    default int count(Integer id) {
        Config config = Config.get(this);
        SelectBuilder builder = SelectBuilder.newInstance(config);
        builder.sql("select count(*) from emp_history where emp_id = ").param(Integer.class, id);
        return builder.getScalarSingleResult(int.class);
    }

    @Sql("select /*%expand*/* from employee where emp_id = /*id*/0")
    @Select
    PostEmployee selectEmpByID(Integer id);

    @Sql("select /*%expand*/* from emp_history " +
            "inner join department on emp_history.dept_id = department.dept_id " +
            "inner join post on emp_history.post_id = post.post_id " +
            "where emp_id = /*id*/0  order by emp_history_id asc")
    @Select
    List<EmpHisReceive> selectEmpHisById(Integer id);


    @Update
    int update(PostEmployee employee);

    @Delete
    int delete(PostEmployee employee);

    default int deleteOver20(Integer id) {
        Config config = Config.get(this);
        SelectBuilder builder = SelectBuilder.newInstance(config);
        builder.sql("delete from emp_history where emp_id =").param(Integer.class, id);
        builder.sql(" order by emp_history_id asc limit 1");
        return builder.getScalarSingleResult(int.class);
    }

}
