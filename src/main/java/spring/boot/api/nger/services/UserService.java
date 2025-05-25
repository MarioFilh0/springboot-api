package spring.boot.api.nger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.enums.Role;
import spring.boot.api.nger.mapper.UserMapper;
import spring.boot.api.nger.dto.UserDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.User;
import spring.boot.api.nger.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity ID:"+id+" not found"));

        return userMapper.toDto(user);
    }

    public void deleteById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity ID:"+id+" not found"));

        user.setDep(null);
        userRepository.save(user);
        userRepository.delete(user);
    }

    public void create(User user) {
        if(user.getRole() == null){
            user.setRole(Role.USER);
        }
        userRepository.save(user);
    }

    public UserDTO update(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity ID:"+id+" not found"));

        userMapper.fromDto(user, dto);
        User newUser = userRepository.save(user);
        return userMapper.toDto(newUser);
    }
}
