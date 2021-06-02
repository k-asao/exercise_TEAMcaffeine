package jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginForm implements Serializable {
    public static final long serialVersionUID= 1L;

    @NotBlank
    String login_id;
    @NotBlank
    String password;
}
