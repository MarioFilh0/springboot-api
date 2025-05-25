package spring.boot.api.nger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.dto.InfoDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.mapper.InfoMapper;
import spring.boot.api.nger.model.Department; // Importe Department
import spring.boot.api.nger.model.Info;
import spring.boot.api.nger.repository.DepartmentRepository; // Importe DepartmentRepository
import spring.boot.api.nger.repository.InfoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoService {
    @Autowired
    InfoRepository infoRepository;
    @Autowired
    InfoMapper infoMapper;
    @Autowired
    DepartmentRepository departmentRepository; // Injetar DepartmentRepository

    // CREATE
    public void create(Info info) {
        if(info != null) {
            // Se a Info tem um department associado, garanta que o departamento existe no banco de dados
            if (info.getDepartment() != null && info.getDepartment().getId() != null) {
                Department existingDepartment = departmentRepository.findById(info.getDepartment().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Department with ID " + info.getDepartment().getId() + " not found."));
                info.setDepartment(existingDepartment); // Garante que a entidade Department é gerenciada
            }
            infoRepository.save(info);
        }
    }
    // READ
    public List<InfoDTO> findAll() {
        return infoRepository.findAll()
                .stream()
                .map(infoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<InfoDTO> findByDepartmentId(Long departmentId) {
        return infoRepository.findByDepartmentId(departmentId)
                .stream()
                .map(infoMapper::toDto)
                .collect(Collectors.toList());
    }


    public InfoDTO findById(Long id) {
        Info info = infoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        return infoMapper.toDto(info);
    }
    // UPDATE
    public InfoDTO update(Long id, InfoDTO dto){
        Info info = infoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        if(dto.getWorkData() != null) {
            info.setWorkData(dto.getWorkData());
        }

        if(dto.getEducationData() != null) {
            info.setEducationData(dto.getEducationData());
        }

        if (dto.getDepartment() != null && dto.getDepartment().getId() != null) {
            Department existingDepartment = departmentRepository.findById(dto.getDepartment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Department with ID " + dto.getDepartment().getId() + " not found."));
            info.setDepartment(existingDepartment);
        } else {
            info.setDepartment(null);
        }

        Info updatedInfo = infoRepository.save(info);
        return infoMapper.toDto(updatedInfo);
    }

    // DELETE
    public void delete(Long id) {
        try{
            infoRepository.deleteById(id);
        }
        catch(EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}