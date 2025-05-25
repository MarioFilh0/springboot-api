package spring.boot.api.nger.mapper;

import org.springframework.stereotype.Service;
import spring.boot.api.nger.dto.UserDTO;
import spring.boot.api.nger.model.User;

@Service
public class UserMapper {

    public UserDTO toDto(User user){
        if(user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        return dto;
    }

    public void fromDto(User user, UserDTO dto){
        if(dto == null) {
            throw new IllegalArgumentException("DTO can not be null!");
        }

        if(dto.getUsername() != null){
            user.setLogin(dto.getUsername());
        }
    }

}
