package spring.boot.api.nger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.api.nger.model.Info;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info, Long> {

    List<Info> findByDepartmentId(Long departmentId);
}
