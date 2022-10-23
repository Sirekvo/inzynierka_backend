package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
