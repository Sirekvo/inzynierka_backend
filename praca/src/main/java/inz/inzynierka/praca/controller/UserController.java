package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.UserRepository;
//import inz.inzynierka.praca.services.UserService;
import inz.inzynierka.praca.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class  UserController {

    @Autowired
    UserRepository userRepository;
//    @Autowired
//    UserService userService;
    @Autowired
    UserServices userServices;

    @GetMapping("/user")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

//    @PostMapping("/user/save")
//    public ResponseEntity<UserEntity>saveUser(@RequestBody UserEntity user){
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
//        return ResponseEntity.created(uri).body(userServices.saveUser(user));
//    }




}
