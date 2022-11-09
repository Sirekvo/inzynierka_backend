package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> {

    String sql = "INSERT INTO series (series_id, header, title, creator, genre, production, premiere, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

//    @Transactional
//    @Query(value="INSERT INTO series (series_id, header, title, creator, genre, production, premiere, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
//    SeriesEntity saveSeries(SeriesEntity seriesEntity){
//        return args -> {
//
//        };
//    }

//    SeriesEntity saveSeries(SeriesEntity seriesEntity);
}
