package java.jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class employeeForm implements Serializable {
    public static final long serialVersionUID= 1L;
    @NotBlank
    String name;

    @NotBlank
    String kana;

    @NotBlank
    String hire_date;

    Integer post_id;
    Integer dept_id;

    @NotBlank
    @Pattern(regexp = "^0\\d{9,10}$")
    String tel;

    @Email
    String email;

}
