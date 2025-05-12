package spring.boot.api.nger.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping(value = "/deps")
public class DepartmentResource {

    @Autowired
    DepartmentService departmentService;

    @PostMapping(value = "/add")
    public ResponseEntity<Department> create(@RequestBody @Valid Department dep){
        departmentService.create(dep);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        List<Department> dep = departmentService.getAll();
        return ResponseEntity.ok().body(dep);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Department> deleteById(@PathVariable Long id){
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){
        Department dep = departmentService.getById(id);
        return ResponseEntity.ok().body(dep);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Department> updateById(@PathVariable Long id, @RequestBody @Valid Department newDep) {
        Department dep = departmentService.update(id, newDep);
        return new ResponseEntity<>(newDep, HttpStatus.OK);
    }


}
