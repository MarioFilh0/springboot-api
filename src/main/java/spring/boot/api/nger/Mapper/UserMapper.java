package spring.boot.api.nger.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.dto.UserDTO;
import spring.boot.api.nger.exceptions.EntityNotFoundException;
import spring.boot.api.nger.model.User;

@Service
public class UserMapper {

    @Autowired
    DepartmentMapper departmentMapper;

    public UserDTO toDto(User user){
        if(user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setDepartmentDTO(departmentMapper.toDto(user.getDep()));
        return dto;
    }

    public User fromDto(UserDTO dto){
        if(dto == null) {
            throw new IllegalArgumentException("DTO can not be null!");
        }
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setDep(departmentMapper.fromDto(dto.getDepartmentDTO()));
        return user;
    }

}
