package spring.boot.api.nger.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.dto.DepartmentDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.model.User;
import spring.boot.api.nger.repository.DepartmentRepository;
import spring.boot.api.nger.repository.UserRepository;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public void deleteById(Long id){
        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(dep);
    }

    public Department create(Department dep) {
        return departmentRepository.save(dep);
    }

    public Department update(Long id, DepartmentDTO dto){
        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        dep.setName(dto.getName());

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        dep.setUser(dto.set);
    }



}
