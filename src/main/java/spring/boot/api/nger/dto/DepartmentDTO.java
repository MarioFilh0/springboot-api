    package spring.boot.api.nger.dto;

    import jakarta.validation.constraints.NotBlank;
    import lombok.*;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public class DepartmentDTO {

        Long id;

        @NotBlank(message = "Nome não pode estar em branco.")
        private String name;
    }
