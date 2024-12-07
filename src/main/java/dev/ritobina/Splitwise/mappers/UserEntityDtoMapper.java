package dev.ritobina.Splitwise.mappers;

import dev.ritobina.Splitwise.dtos.UserSignUpDTO;
import dev.ritobina.Splitwise.models.User;

public class UserEntityDtoMapper {

    public static User dtoToEntity(UserSignUpDTO userSignUpDTO){
        User user = new User();
        user.setUsername(userSignUpDTO.getUserName());
        user.setPassword(userSignUpDTO.getPassword());
        user.setPassword(userSignUpDTO.getEmail());
        return user;
    }
}
