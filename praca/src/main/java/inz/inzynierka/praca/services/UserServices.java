package inz.inzynierka.praca.services;

import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.entites.UserEntity;

import java.util.List;

public interface UserServices {
    UserEntity saveUser(UserEntity userEntity);
    SeriesEntity saveSeries(SeriesEntity seriesEntity);
    void updatePost(SeriesEntity seriesEntity);
    List<SeriesEntity> searchByTitle(String title);

}
