package dev.ritobina.Splitwise.controllers;

import dev.ritobina.Splitwise.dtos.UserSignUpDTO;
import dev.ritobina.Splitwise.exceptions.UserValidationException;
import dev.ritobina.Splitwise.mappers.UserEntityDtoMapper;
import dev.ritobina.Splitwise.models.User;
import dev.ritobina.Splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserSignUpDTO userSignUpDTO){
        //validations
        signUpValidations(userSignUpDTO);
        User newUser = UserEntityDtoMapper.dtoToEntity(userSignUpDTO);
        newUser = userService.createUser(newUser);
        return ResponseEntity.ok(newUser);
    }

    private void signUpValidations(UserSignUpDTO userSignUpDTO){
        if(userSignUpDTO.getUserName()==null){
            throw new UserValidationException("Username is null, please provide a valid username");
        }
        String password = userSignUpDTO.getPassword();
        if(password==null){
            throw new UserValidationException("Password not created, please create a password");
        }
        if (!((password.length() >= 8) && (password.length() <= 15))){
            throw new UserValidationException("Password has less than 8 or more than 15 characters.");
        }
        if (true) {
            int count = 0;
            // check digits from 0 to 9
            for (int i = 0; i <= 9; i++) {
                // to convert int to string
                String str1 = Integer.toString(i);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                throw new UserValidationException("Password must contain a digit from 0-9");
            }
        }
    }
}
