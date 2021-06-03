package jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ConfPassForm implements Serializable {
    public static final long serialVersionUID= 1L;


    String email;

    @NotBlank
    @Length(min=6, max=73, message = "6文字以上73文字以内で入力して下さい。")
    String pass;


    @NotBlank
    String passConf;
}
