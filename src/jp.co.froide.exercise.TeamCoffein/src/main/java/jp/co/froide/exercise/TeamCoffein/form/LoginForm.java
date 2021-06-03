package jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginForm implements Serializable {
    public static final long serialVersionUID= 1L;

    @NotBlank
    String login_id;

    @Length(min=6, max=73, message = "6文字以上73文字以内で入力して下さい。")
    @NotBlank
    String password;
}
