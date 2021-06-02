package jp.co.froide.exercise.TeamCoffein.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@JsonPropertyOrder({"emp_id", "name", "kana", "hire_date", "post_name", "dept_name", "tel", "email"})
public class CsvView {
    @JsonProperty("emp_id")
    private long emp_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("kana")
    private String kana;
    @JsonProperty("hire_date")
    private String hire_date;
    @JsonProperty("post_name")
    private String post_name;
    @JsonProperty("dept_name")
    private String dept_name;
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("email")
    private String email;
}
