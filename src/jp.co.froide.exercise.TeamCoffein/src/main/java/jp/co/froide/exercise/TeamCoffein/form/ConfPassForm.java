package jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class ConfPassForm implements Serializable {
    public static final long serialVersionUID= 1L;


    String email;

    @NotBlank
    @Pattern(regexp = "^([a-zA-Z0-9]{6,72})$", message = "6~72文字の半角英数字で入力して下さい。")
    String pass;


    @NotBlank
    String passConf;
}
