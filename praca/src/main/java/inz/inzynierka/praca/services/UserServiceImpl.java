package inz.inzynierka.praca.services;

import inz.inzynierka.praca.entites.UserEntity;
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

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserServices, UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

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
