package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.entites.SlidersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SlidersRepository extends JpaRepository<SlidersEntity, Long> {


    @Transactional
    @Modifying
    @Query(value="UPDATE sliders SET url = (?2) WHERE slider_id = (?1)", nativeQuery = true)
    void updateSlider(Long slider_id, String url);
    @Transactional
    @Modifying
    @Query(value="DELETE FROM sliders WHERE slider_id = (?1)", nativeQuery = true)
    void deleteSlider(Long id);
}
