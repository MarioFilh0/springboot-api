package spring.boot.api.nger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.model.User;
import spring.boot.api.nger.repository.DepartmentRepository;
import spring.boot.api.nger.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public void deleteById(Long id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User nuevo) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(nuevo.getName());
        user.setEmail(nuevo.getEmail());
        user.setDep(nuevo.getDep());

        return userRepository.save(user);
    }



}
