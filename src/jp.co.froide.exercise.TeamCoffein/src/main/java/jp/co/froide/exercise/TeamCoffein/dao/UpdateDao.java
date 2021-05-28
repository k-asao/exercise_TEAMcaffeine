package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

//更新・削除用Dao
@ConfigAutowireable
@Dao
public interface UpdateDao {

    @Sql("select /*%expand*/* from employee where emp_id = /*id*/0")
    @Select
    PostEmployee selectEmpByID(Integer id);


    @Update
    int update(PostEmployee employee);

    @Delete
    int delete(PostEmployee employee);
}
