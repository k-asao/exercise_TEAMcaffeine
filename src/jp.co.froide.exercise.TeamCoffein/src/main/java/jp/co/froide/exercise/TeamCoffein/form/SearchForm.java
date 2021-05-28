package jp.co.froide.exercise.TeamCoffein.form;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchForm implements Serializable {

//    入力するもの
//    検索フォームにある４つ
//    変数宣言
//    htmlからの入力を受け取るようにする
    String order;
    String name;
    Integer post_id;
    Integer dept_id;
    String hire_date;
}
