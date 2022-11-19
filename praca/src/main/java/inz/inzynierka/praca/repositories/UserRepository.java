package inz.inzynierka.praca.repositories;

import inz.inzynierka.praca.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    @Modifying
    @Query(value="UPDATE users SET password = (?2) WHERE account_id = (?1)", nativeQuery = true)
    void changePassword(Long account_id, String password);

    @Transactional
    @Modifying
    @Query(value="UPDATE users SET email = (?2), name = (?3), lastname = (?4) WHERE account_id = (?1)", nativeQuery = true)
    void changeInformation(Long account_id, String email, String name, String lastname);

//    @Transactional
//    @Modifying
//    @Query(value="SELECT * FROM USER WHERE email = (?1)", nativeQuery = true)
//    void getInformationAboutUser(String email);


    //    @Override
//    Optional<UserEntity> findByEmail(String email);
    UserEntity findByEmail(String email);
}
