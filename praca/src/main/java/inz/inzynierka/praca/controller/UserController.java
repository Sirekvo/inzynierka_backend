package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.SeriesRepository;
import inz.inzynierka.praca.repositories.SlidersRepository;
import inz.inzynierka.praca.repositories.UserRepository;
//import inz.inzynierka.praca.services.UserService;
import inz.inzynierka.praca.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

import java.util.List;

@RestController
public class  UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    SlidersRepository slidersRepository;
    @Autowired
    UserServices userServices;

    @GetMapping("/users")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/user")
    public void createUser(@RequestBody UserEntity userEntity){
        userServices.saveUser(userEntity);
    }
    @PutMapping("/change-password")
    public void changePassword(@RequestBody UserEntity userEntity ){
        userServices.changePassword(userEntity);
    }
    @PutMapping("/change-information")
    public void changeInformation(@RequestBody UserEntity userEntity ){
        userServices.changeInformation(userEntity);
    }


    @DeleteMapping("/delete-user/{account_id}")
    public void deleteUSer(@PathVariable("account_id") Long id){
        userRepository.deleteById(id);
    }

//    @PostMapping("/user/save")
//    public ResponseEntity<UserEntity>saveUser(@RequestBody UserEntity user){
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
//        return ResponseEntity.created(uri).body(userServices.saveUser(user));
//    }




}
