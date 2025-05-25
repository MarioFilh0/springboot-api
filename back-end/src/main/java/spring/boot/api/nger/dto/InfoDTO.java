package spring.boot.api.nger.dto;

import lombok.*;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.model.EducationData;
import spring.boot.api.nger.model.WorkData;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class InfoDTO {
    private Long id;
    private Department department;
    private WorkData workData;
    private EducationData educationData;
}
