package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @Override
//    Optional<UserEntity> findByEmail(String email);
    UserEntity findByEmail(String email);
}
