package jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ConfPassForm implements Serializable {
    public static final long serialVersionUID= 1L;


    String email;

    @NotBlank
    String pass;

    @NotBlank
    String passConf;
}
