package jp.co.froide.exercise.TeamCoffein.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ConfMailForm implements Serializable {
    public static final long serialVersionUID= 1L;

    @NotBlank
    String email;
}
