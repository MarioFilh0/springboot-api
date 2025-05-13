package spring.boot.api.nger.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDTO {


    private Long id;

    @NotBlank(message = "Nome não pode estar em branco.")
    private String name;

    @Email
    @NotBlank(message = "Email não pode estar em branco.")
    private String email;

    private DepartmentDTO departmentDTO;
}
