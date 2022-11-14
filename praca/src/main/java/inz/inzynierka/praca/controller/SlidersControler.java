package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.SlidersEntity;
import inz.inzynierka.praca.repositories.SeriesRepository;
import inz.inzynierka.praca.repositories.SlidersRepository;
import inz.inzynierka.praca.repositories.UserRepository;
import inz.inzynierka.praca.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SlidersControler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    SlidersRepository slidersRepository;
    @Autowired
    UserServices userServices;
    @PostMapping("/sliders")
    public void setSlider(@RequestBody SlidersEntity sliders){
        userServices.saveSlider(sliders);
    }
    @GetMapping("/sliders")
    public List<SlidersEntity> getSliders(){
        return slidersRepository.findAll();
    }
}
