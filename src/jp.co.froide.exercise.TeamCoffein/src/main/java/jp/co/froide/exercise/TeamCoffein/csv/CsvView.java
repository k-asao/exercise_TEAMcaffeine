package jp.co.froide.exercise.TeamCoffein.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@JsonPropertyOrder({"社員番号", "名前", "フリガナ", "入社年月日", "役職", "所属部署", "電話番号", "メールアドレス"})
public class CsvView {
    @JsonProperty("社員番号")
    private long emp_id;
    @JsonProperty("名前")
    private String name;
    @JsonProperty("フリガナ")
    private String kana;
    @JsonProperty("入社年月日")
    private String hire_date;
    @JsonProperty("役職")
    private String post_name;
    @JsonProperty("所属部署")
    private String dept_name;
    @JsonProperty("電話番号")
    private String tel;
    @JsonProperty("メールアドレス")
    private String email;
}
