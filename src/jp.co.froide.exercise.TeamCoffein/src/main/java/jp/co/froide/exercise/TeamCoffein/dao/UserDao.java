package jp.co.froide.exercise.TeamCoffein.dao;

import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.io.IOException;
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

    @Sql("select hire_date  from employee group by hire_date order by hire_date asc")
    @Select
    List<String> selectEmpAll();

    @Sql("select /*%expand*/* from employee inner join department on employee.dept_id = department.dept_id " +
            "inner join post on employee.post_id = post.post_id where /*%if name != \"\" */ " +
            "name like /* @infix(name) */'smith'/*%end*/" +
            "/*%if hire_date != \"\" */ and employee.hire_date = /* hire_date */99 /*%end*/" +
            "/*%if post_id != null */ and employee.post_id = /* post_id */99 /*%end*/" +
            "/*%if dept_id != null */ and employee.dept_id = /* dept_id */99 /*%end*/" +
            "order by  emp_id /*# order */ limit /*# lim */ offset /*# off */")
    @Select
    List<Employee> selectSearchAll(String order, String name, Integer post_id, Integer dept_id, String hire_date,
Integer lim, Integer off);

    @Sql("select /*%expand*/* from employee where id = /* id */0")
    @Select
    Employee selectById(Integer id);

    @Sql("select /*%expand*/* from department")
    @Select
    List<Department> selectDeptAll();

    @Sql("select /*%expand*/* from post")
    @Select
    List<Post> selectPostAll();

    @Sql("select count(*) from employee")
    @Select
    int getMemberListCount();

    @Sql("select count(*) from employee inner join department on employee.dept_id = department.dept_id " +
            "inner join post on employee.post_id = post.post_id where /*%if name != \"\" */ " +
            "name like /* @infix(name) */'smith'/*%end*/" +
            "/*%if hire_date != \"\" */ and employee.hire_date = /* hire_date */99 /*%end*/" +
            "/*%if post_id != null */ and employee.post_id = /* post_id */99 /*%end*/" +
            "/*%if dept_id != null */ and employee.dept_id = /* dept_id */99 /*%end*/" +
            "order by  emp_id /*# order */")
    @Select
    int getSearch(String order, String name, Integer post_id, Integer dept_id, String hire_date);


//    @Sql("select /*%expand*/*  from employee inner join department on employee.dept_id = department.dept_id inner " +
//            "join post on employee.post_id = post.post_id limit 20")
//    @Select
//    List<Employee> getMemberList();

//    default List<Employee> getMemberList(HashMap<String, String> search) throws SQLException, IOException {
//        Config config = Config.get(this);
//        SelectBuilder builder = SelectBuilder.newInstance(config);
//        int limit = Integer.valueOf(search.get("limit"));
//        int page = Integer.valueOf(search.get("page")) - 1;
//        int offset = limit * page;
//        System.out.println(limit);
//
//        builder.sql("select emp_id, name, kana, hire_date, post_name, dept_name, tel, email " +
//                "from (select emp_id, name, kana, hire_date, post_name, dept_name, tel, email " +
//                "from employee inner join department on employee.dept_id = department.dept_id " +
//                "inner join post on employee.post_id = post.post_id order by emp_id asc) as emp " );
//        builder.sql(" limit ").param(Integer.class, limit);
//        builder.sql(" offset ").param(Integer.class, offset);
//
//        System.out.println(builder.getSql());
//
//        List<Employee> emp = builder.getEntityResultList(Employee.class);
//
//
////        builder.addParameter(String, "limit");
////        emp.addParameter(limit * page, "offset");
//
//
//        return emp;
//    }


    @Insert
    int insert(Employee emp);

    @Update
    int update(Employee emp);

    @Delete
    int delete(Employee emp);
}
