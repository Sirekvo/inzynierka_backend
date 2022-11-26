package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.BoolForm;
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
    public void changePassword(@RequestBody UserEntity userEntity, Principal principal ){
        userServices.changePassword(userEntity, principal.getName());
    }
    @PutMapping("/change-information")
    public void changeInformation(@RequestBody UserEntity userEntity, Principal principal ){
        userServices.changeInformation(userEntity, principal.getName());
    }
    @DeleteMapping("/delete-user/{account_id}")
    public void deleteUSer(@PathVariable("account_id") Long id){
        userServices.deleteUser(id);
    }

    @GetMapping("/user")
    public String getUserInformation(Principal principal) {
        return userServices.getInformationAboutUser(principal.getName());
    }
    @GetMapping("/check-email/{email}")
    public BoolForm checkEmail(@PathVariable("email") String email) {
        return userServices.checkEmail(email);
    }
    @PutMapping("/change-view")
    public void changeView(@RequestBody UserEntity userEntity, Principal principal ){
        userServices.changeView(userEntity, principal.getName());
    }
//    @PostMapping("/user/save")
//    public ResponseEntity<UserEntity>saveUser(@RequestBody UserEntity user){
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
//        return ResponseEntity.created(uri).body(userServices.saveUser(user));
//    }




}
