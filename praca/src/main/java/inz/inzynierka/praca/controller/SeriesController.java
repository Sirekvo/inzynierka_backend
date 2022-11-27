package inz.inzynierka.praca.controller;

import inz.inzynierka.praca.entites.CommentsEntity;
import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.repositories.CommentsRepository;
import inz.inzynierka.praca.repositories.SeriesRepository;
import inz.inzynierka.praca.repositories.SlidersRepository;
import inz.inzynierka.praca.repositories.UserRepository;
import inz.inzynierka.praca.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SeriesController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    SlidersRepository slidersRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    UserServices userServices;

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
    }
    @PostMapping("/comment")
    public void setComment(@RequestBody CommentsEntity commentsEntity){
        userServices.saveComment(commentsEntity);
    }
    @GetMapping("/comment/{series_id}")
    public List<CommentsEntity> getCommentsBySeriesId(@PathVariable("series_id") Long id){
        return userServices.searchBySeriesId(id);
    }
    @DeleteMapping("/comment/{comment_id}")
    public void deleteComment(@PathVariable("comment_id") Long id){
        commentsRepository.deleteById(id);
    }
}
