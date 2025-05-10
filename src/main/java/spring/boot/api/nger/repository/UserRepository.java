package spring.boot.api.nger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.api.nger.resource.UserResource;

@Repository
public interface UserRepository extends JpaRepository<UserResource, Long> {

}
