package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;

//新規登録用Dao
@ConfigAutowireable
@Dao
public interface InsertDao {

    @Insert
    int insert(PostEmployee emp);

}
