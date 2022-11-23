package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.SlidersEntity;
import inz.inzynierka.praca.repositories.SeriesRepository;
import inz.inzynierka.praca.repositories.SlidersRepository;
import inz.inzynierka.praca.repositories.UserRepository;
import inz.inzynierka.praca.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void setSlider(@RequestBody List<SlidersEntity> paramValues){
//        userServices.saveSlider(paramValues);
        slidersRepository.deleteAll();
        slidersRepository.saveAll(paramValues);
    }
    @GetMapping("/sliders")
    public List<SlidersEntity> getSliders(){
        return slidersRepository.findAll();
    }
    @PutMapping("/sliders")
    public void updateSlider(@RequestBody SlidersEntity sliders ){
        userServices.updateSlider(sliders);
    }
    @DeleteMapping("/sliders/{slider_id}")
    public void deleteSlider(@PathVariable("slider_id") Long id){
        userServices.deleteSlider(id);
    }
}
