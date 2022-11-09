package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> {

    String sql = "INSERT INTO series (series_id, header, title, creator, genre, production, premiere, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Transactional
    @Modifying
    @Query(value="UPDATE series SET title = (?2), creator = (?3), genre = (?4), production = (?5), premiere = (?6), description = (?7), url = (?8) WHERE series_id = ?1", nativeQuery = true)
    void  updatePost(Long series_id, String title, String creator, String genre, String production,
                                   String premiere, String description, String url);
//    public void updatePost(@Param("series_id") Long series_id, @Param("title") String title, @Param("creator") String creator,
//                           @Param("genre") String genre, @Param("production") String production, @Param("description") String description,
//                           @Param("url") String url);

//    SeriesEntity saveSeries(SeriesEntity seriesEntity);
}
