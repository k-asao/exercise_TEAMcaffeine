package jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class EmployeeForm implements Serializable {
    public static final long serialVersionUID= 1L;
    @Length(min=0, max=255)
    @NotBlank
    String name;

    @Length(min=0, max=255)
    @Pattern(regexp = "[ァ-タダ-ヶー]+", message = "全角カナで入力してください")
    String kana;

    @NotBlank
    String hire_date;

    @NotNull
    Integer post_id;

    @NotNull
    Integer dept_id;

    @Pattern(regexp = "^0\\d{10,11}$", message = "半角数字で電話番号を入力してください。")
    String tel;

    @Length(min=0, max=255)
    @Email
    @NotBlank
    String email;

}
