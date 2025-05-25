package spring.boot.api.nger.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.api.nger.dto.DepartmentDTO;
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
    public ResponseEntity<List<DepartmentDTO>> findAll(){
        List<DepartmentDTO> dto = departmentService.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Department> deleteById(@PathVariable Long id){
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id) {
        departmentService.findById(id);
        return ResponseEntity.ok().body(departmentService.findById(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @RequestBody @Valid DepartmentDTO dto) {
        DepartmentDTO newDTO = departmentService.update(id, dto);
        return new ResponseEntity<>(newDTO, HttpStatus.OK);
    }


}
