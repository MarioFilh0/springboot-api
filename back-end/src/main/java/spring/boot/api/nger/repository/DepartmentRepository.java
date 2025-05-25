package spring.boot.api.nger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.api.nger.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
