package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Post;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

//役職テーブル用のDao
@ConfigAutowireable
@Dao
public interface PostDao {
    @Sql("select /*%expand*/* from post")
    @Select
    List<Post> selectAll();
}
