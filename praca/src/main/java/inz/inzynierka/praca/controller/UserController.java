package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }
}
