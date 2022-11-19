package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM comments WHERE serie_id = ?1", nativeQuery = true)
    List<CommentsEntity> searchBySeriesId(Long id);
}
