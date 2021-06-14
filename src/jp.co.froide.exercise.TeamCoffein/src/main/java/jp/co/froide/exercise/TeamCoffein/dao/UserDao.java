package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.entity.Year;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
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

    @Sql("select EXTRACT(year FROM hire_date) AS year from employee GROUP BY EXTRACT(year FROM hire_date)")
    @Select
    List<Year> selectHireDateAll() ;

    @Sql("select /*%expand*/* from employee inner join department on employee.dept_id = department.dept_id " +
            "inner join post on employee.post_id = post.post_id and employee.delete_flag = /* delete_flag */0 " +
            "where /*%if name != \"\" */ (name like /* @infix(name) */'smith' or kana like /* @infix(name) */'smi')/*%end*/" +
            "/*%if hire_date != \"\" */ and employee.hire_date between /* hire_date + \"-01-01 00:00:00\" */0  AND /* hire_date + \"-12-31 00:00:00\" */0 /*%end*/" +
            "/*%if post_id != null */ and employee.post_id = /* post_id */99 /*%end*/" +
            "/*%if dept_id != null */ and employee.dept_id = /* dept_id */99 /*%end*/" +
            "order by  emp_id /*# order */ limit /*# lim */ offset /*# off */")
    @Select
    List<Employee> selectSearchAll(String order, String name, Integer post_id, Integer dept_id, String hire_date,
                                   Integer lim, Integer off, Integer delete_flag);

    @Sql("select /*%expand*/* from department")
    @Select
    List<Department> selectDeptAll();

    @Sql("select /*%expand*/* from post")
    @Select
    List<Post> selectPostAll();

    @Sql("select count(*) from employee")
    @Select
    int getMemberListCount();

    @Sql("select count(*) from employee where delete_flag = 1")
    @Select
    int getRemovedMemberListCount();


    @Sql("select count(*) from employee inner join department on employee.dept_id = department.dept_id " +
            "inner join post on employee.post_id = post.post_id and employee.delete_flag = /* delete_flag */0 " +
            "where /*%if name != \"\" */ (name like /* @infix(name) */'smith' or kana like /* @infix(name) */'smi')/*%end*/" +
            "/*%if hire_date != \"\" */ and employee.hire_date between /* hire_date + \"-01-01 00:00:00\" */0  AND /* hire_date + \"-12-31 00:00:00\" */0 /*%end*/" +
            "/*%if post_id != null */ and employee.post_id = /* post_id */99 /*%end*/" +
            "/*%if dept_id != null */ and employee.dept_id = /* dept_id */99 /*%end*/" +
            "order by  emp_id /*# order */")
    @Select
    int getSearch(String order, String name, Integer post_id, Integer dept_id, String hire_date, Integer delete_flag) throws ConnectException;

}
