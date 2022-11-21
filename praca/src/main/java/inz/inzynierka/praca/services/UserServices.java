package inz.inzynierka.praca.services;

import inz.inzynierka.praca.entites.CommentsEntity;
import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.entites.SlidersEntity;
import inz.inzynierka.praca.entites.UserEntity;

import java.util.List;

public interface UserServices {
    UserEntity saveUser(UserEntity userEntity);
    
    SeriesEntity saveSeries(SeriesEntity seriesEntity);
    
    void updatePost(SeriesEntity seriesEntity);
    
    List<SeriesEntity> searchByTitle(String title);
    
    SlidersEntity saveSlider(SlidersEntity slidersEntity);

    void changePassword(UserEntity userEntity, String name);

    void changeInformation(UserEntity userEntity, String name);

    CommentsEntity saveComment(CommentsEntity commentsEntity);

    List<CommentsEntity> searchBySeriesId(Long id);

    String getInformationAboutUser(String email);

    void deleteUser(Long id);

    void updateSlider(SlidersEntity sliders);

    void deleteSlider(Long id);
}
