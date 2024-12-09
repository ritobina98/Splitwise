package dev.ritobina.Splitwise.services;

import dev.ritobina.Splitwise.dtos.UserSignUpDTO;
import dev.ritobina.Splitwise.exceptions.UserNotFoundException;
import dev.ritobina.Splitwise.models.User;
import dev.ritobina.Splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id "+id+" is not found")
        );
    }
}
