package spring.boot.api.nger.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.Mapper.DepartmentMapper;
import spring.boot.api.nger.dto.DepartmentDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
    }

    public void deleteById(Long id){
        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        departmentRepository.delete(dep);
    }

    public void create(Department department){
        departmentRepository.save(department);
    }

    public Department update(Long id, DepartmentDTO dto){
        //Busca o usuário
        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        //Converte DTO em Entity para salvar no banco
        Department update = departmentMapper.fromDto(dto);

        //Aplica as atualizações
        dep.setName(update.getName());

        //Salva a atualização
        return departmentRepository.save(dep);
    }



}
