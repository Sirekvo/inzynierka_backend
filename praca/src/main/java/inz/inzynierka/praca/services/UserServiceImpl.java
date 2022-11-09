package inz.inzynierka.praca.services;

import inz.inzynierka.praca.entites.SeriesEntity;
import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.SeriesRepository;
import inz.inzynierka.praca.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserServices, UserDetailsService {
    private final UserRepository userRepository;
    private final SeriesRepository seriesRepository;

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

//    @Override
//    public void saveSeries(List<SeriesEntity> series) {
//        for (SeriesEntity x : series) {
//            seriesRepository.save(x.getTitle(), x.getCreator(), x.getGenre(), x.getProduction(), x.getPremiere(), x.getDescription());
//        }
//    }

    @Override
    public SeriesEntity saveSeries(SeriesEntity seriesEntity) {
        return seriesRepository.save(seriesEntity);
    }

//    @Override
//    public SeriesEntity getSeries(Long id) {
//        Optional<SeriesEntity> studentResponse =  seriesRepository.findById(id);
//        SeriesEntity series = null;
//        if(studentResponse.isPresent()) {
//            series = studentResponse.get();
//        }else {
//            throw new RuntimeException("No record found for given id: "+id);
//        }
//
//        return series;
//    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email);
        if(user == null){
            log.error("brak usera w bazie");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User znaleziony w bazie");
        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
