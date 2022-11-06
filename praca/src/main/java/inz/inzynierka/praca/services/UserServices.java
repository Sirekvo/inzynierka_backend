package inz.inzynierka.praca.services;

import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.entites.UserEntity;

public interface UserServices {
    UserEntity saveUser(UserEntity userEntity);
    SeriesEntity saveSeries(SeriesEntity seriesEntity);

}
