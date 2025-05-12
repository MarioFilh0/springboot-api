package spring.boot.api.nger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.User;
import spring.boot.api.nger.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    public void deleteById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        user.setDep(null);
        userRepository.save(user);
        userRepository.delete(user);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void update(Long id, User nuevo) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        user.setName(nuevo.getName());
        user.setEmail(nuevo.getEmail());
        user.setDep(nuevo.getDep());

        userRepository.save(user);
    }



}
