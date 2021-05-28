package jp.co.froide.exercise.TeamCoffein.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.*;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {

    @Id
    Integer post_id;

    String post_name;
}
