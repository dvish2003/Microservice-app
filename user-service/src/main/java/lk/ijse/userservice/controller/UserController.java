package lk.ijse.userservice.controller;

import lk.ijse.userservice.model.ResponseDTO;
import lk.ijse.userservice.model.User;
import lk.ijse.userservice.repo.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepo userRepository;

    public UserController(UserRepo userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/getAll")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @PostMapping(value = "/saveUser")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody User user){
        System.out.println("sgdfjhksdgkdjskgjlfshg"+user);

        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity
                    .status(409)
                    .body(new ResponseDTO("User Already Exists", null, 409));
        }

        User savedUser = userRepository.save(user);

        return ResponseEntity
                .status(201)
                .body(new ResponseDTO("User Created Successfully", savedUser, 201));
    }

    @PostMapping("/getUserByEmail")
    public User getUserById(@RequestBody User user){
        return (User) userRepository.findByEmail(user.getEmail()).orElse(null);
    }

    @PostMapping("/checkExist")
    public boolean checkExist(@RequestBody User user){
        return userRepository.existsByEmail(user.getEmail());
    }
}