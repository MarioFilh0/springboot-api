package spring.boot.api.nger.controller;


import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.api.nger.Mapper.DepartmentMapper;
import spring.boot.api.nger.dto.DepartmentDTO;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.services.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/deps")
public class DepartmentResource {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<Department> create(@RequestBody @Valid Department dep){
        departmentService.create(dep);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll(){
        List<DepartmentDTO> dto = departmentService.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Department> deleteById(@PathVariable Long id){
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id) {
        Department dep = departmentService.findById(id);
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(dep.getName());
        dto.setId(dto.getId());
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @RequestBody @Valid DepartmentDTO dto) {
        Department updated = departmentService.update(id, dto);
        DepartmentDTO function = departmentMapper.toDto(updated);
        return new ResponseEntity<>(function, HttpStatus.OK);
    }


}
