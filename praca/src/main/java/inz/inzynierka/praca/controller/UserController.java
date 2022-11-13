package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.SeriesRepository;
import inz.inzynierka.praca.repositories.UserRepository;
//import inz.inzynierka.praca.services.UserService;
import inz.inzynierka.praca.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class  UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    UserServices userServices;

    @GetMapping("/user")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/series")
    public void setSeries(@RequestBody SeriesEntity series){
        userServices.saveSeries(series);
    }
    @GetMapping("/series")
    public List<SeriesEntity> getSeries(){
        return seriesRepository.findAll();
    }
    @DeleteMapping("/series/{series_id}")
    public void deleteSeries(@PathVariable("series_id") Long id){
        seriesRepository.deleteById(id);
    }
    @PutMapping("change-post")
    public void changePost(@RequestBody SeriesEntity series) {
        userServices.updatePost(series);
    }

    @GetMapping("/series/{series_id}")
    public Optional<SeriesEntity> getSeries(@PathVariable("series_id") Long id){
        return seriesRepository.findById(id);
    }

    @GetMapping("/findBy/{title}")
    public List<SeriesEntity> getSeriesByTitle(@PathVariable("title") String title){

        return userServices.searchByTitle(title);
//        return seriesRepository.findByTitleStartingWith(title);
    }
//    @PostMapping("/user/save")
//    public ResponseEntity<UserEntity>saveUser(@RequestBody UserEntity user){
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
//        return ResponseEntity.created(uri).body(userServices.saveUser(user));
//    }




}
