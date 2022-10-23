package inz.inzynierka.praca.services;


import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
//    public AppUser getUser(String email) {
//        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user with email " + email + "not found"));
//    }

//    User getUmail(String email);
//    List<User> getUsers()
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        int number;
//        int index;
//        String[] password;
//        String _email = email;
//        index = _email.indexOf("_");
//        number = Integer.parseInt(_email.substring(0, index));
//        _email = _email.substring(index+1);
//
//        UserEntity user = userRepository.findByEmail(_email);
//
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        //authorityList.add(new SimpleGrantedAuthority(user.getAuthority()));
//
//        password = user.getPass();
//
//        return new User(email, password[number], authorityList);
//    }

}
