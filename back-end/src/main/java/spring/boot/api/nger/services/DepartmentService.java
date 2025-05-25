package spring.boot.api.nger.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.exceptions.GlobalExceptionHandler;
import spring.boot.api.nger.mapper.DepartmentMapper;
import spring.boot.api.nger.dto.DepartmentDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.repository.DepartmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepartmentDTO findById(Long id){
        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity ID:"+id+" not found"));

        return departmentMapper.toDto(dep);
    }

    public void deleteById(Long id){
        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity ID:"+id+" not found"));
        departmentRepository.delete(dep);
    }

    public void create(Department department){
        departmentRepository.save(department);
    }

    public DepartmentDTO update(Long id, DepartmentDTO dto){
        Department dep = departmentRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Entity ID:"+id+" not found"));

        departmentMapper.fromDto(dep, dto);
        Department updated = departmentRepository.save(dep);
        return departmentMapper.toDto(updated);
    }



}
