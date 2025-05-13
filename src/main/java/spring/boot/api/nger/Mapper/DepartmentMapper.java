package spring.boot.api.nger.Mapper;

import org.springframework.stereotype.Service;
import spring.boot.api.nger.dto.DepartmentDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.Department;

@Service
public class DepartmentMapper {

    public DepartmentDTO toDto(Department dep) {
        if(dep == null){
            throw new EntityNotFoundException("Department not found");
        }
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dep.getId());
        dto.setName(dep.getName());
        return dto;
    }

    public Department fromDto(DepartmentDTO dto) {
        if(dto == null) {
            throw new IllegalArgumentException("DTO can not be null!");
        }

        Department dep = new Department();
        dep.setName(dto.getName());
        return dep;
    }
}
