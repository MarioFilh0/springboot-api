package spring.boot.api.nger.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.dto.InfoDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.model.Info;
import spring.boot.api.nger.repository.DepartmentRepository;

@Service
public class InfoMapper {

    @Autowired
    DepartmentRepository departmentRepository;

    public InfoDTO toDto(Info info) {
        if(info == null){
            throw new EntityNotFoundException("Table info not found");
        }
        InfoDTO dto = new InfoDTO();

        if(info.getId() != null){
            dto.setId(info.getId());
        }

        if(info.getEducationData() != null) {
            dto.setEducationData(info.getEducationData());
        }

        if(info.getWorkData() != null){
            dto.setWorkData(info.getWorkData());
        }

        if(info.getDepartment() != null && info.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(info.getDepartment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Not found"));

            dto.setDepartment(department);
        }
        return dto;
    }

    public Info fromDto(Info info, InfoDTO dto) {
        if(dto == null){
            throw new EntityNotFoundException("Table info not found");
        }

        if(dto.getEducationData() != null){
            info.setEducationData(dto.getEducationData());
        }

        if(dto.getWorkData() != null) {
            info.setWorkData(dto.getWorkData());
        }

        if(dto.getDepartment() != null && dto.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(dto.getDepartment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Not found"));

            info.setDepartment(department);
        }
        return info;
    }
}
