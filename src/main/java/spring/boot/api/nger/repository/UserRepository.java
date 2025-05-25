package spring.boot.api.nger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import spring.boot.api.nger.model.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

}

