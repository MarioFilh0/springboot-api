package spring.boot.api.nger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.api.nger.dto.InfoDTO;
import spring.boot.api.nger.model.Info;
import spring.boot.api.nger.services.InfoService;

import java.util.List;

@RestController
@RequestMapping("/informations")
public class InfoResources {

    @Autowired
    private InfoService infoService;

    @PostMapping
    public ResponseEntity<Info> crate(@RequestBody Info info) {
        infoService.create(info);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoDTO> update(@PathVariable Long id, @RequestBody InfoDTO dto) {
        InfoDTO newDTO = infoService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(newDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Info> delete(@PathVariable Long id) {
        infoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<InfoDTO>> findAll() {
        List<InfoDTO> dto = infoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // NOVO: Endpoint para buscar informações por ID de departamento
    @GetMapping("/byDepartment/{departmentId}")
    public ResponseEntity<List<InfoDTO>> findByDepartmentId(@PathVariable Long departmentId) {
        List<InfoDTO> dto = infoService.findByDepartmentId(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoDTO> findById(@PathVariable Long id) {
        infoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(infoService.findById(id));
    }
}