package spring.boot.api.nger.services;


import org.springframework.stereotype.Service;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getById(Long id){
        return Optional.ofNullable(departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found")));
    }

    public void deleteById(Long id){
        departmentRepository.deleteById(id);
    }

    public Department create(Department dep) {
        return departmentRepository.save(dep);
    }

    public Department update(Long id, Department department){
        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        dep.setUser(department.getUser());
        dep.setName(department.getName());
        return departmentRepository.save(dep);
    }



}
