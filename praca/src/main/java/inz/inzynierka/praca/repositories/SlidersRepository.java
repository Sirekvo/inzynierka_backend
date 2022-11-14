package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.entites.SlidersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlidersRepository extends JpaRepository<SlidersEntity, Long> {
}
