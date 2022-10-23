package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.JSON.LoginModel;
import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.UserRepository;
import inz.inzynierka.praca.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class  UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

}
