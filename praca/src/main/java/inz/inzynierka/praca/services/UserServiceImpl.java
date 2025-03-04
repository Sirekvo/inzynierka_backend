package inz.inzynierka.praca.services;

import com.google.gson.JsonObject;
import inz.inzynierka.praca.entites.*;
import inz.inzynierka.praca.repositories.*;
import inz.inzynierka.praca.secruity.JwTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserServices, UserDetailsService {
    private final UserRepository userRepository;
    private final SeriesRepository seriesRepository;
    private final SlidersRepository slidersRepository;
    private final CommentsRepository commentsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserEntity user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setName(user.getName());
        userEntity.setLastname(user.getLastname());
        userEntity.setRole(user.getRole());

        return userRepository.save(userEntity);
    }

    @Override
    public SeriesEntity saveSeries(SeriesEntity seriesEntity) {
        return seriesRepository.save(seriesEntity);
    }

    @Override
    public void updatePost(SeriesEntity series) {
        seriesRepository.updatePost(series.getSeries_id(),series.getTitle(),series.getCreator(),series.getGenre(),
                series.getProduction(),series.getPremiere(),series.getDescription(),series.getUrl());
    }

    @Override
    public List<SeriesEntity> searchByTitle(String title) {
        return seriesRepository.searchByTitle(title);
    }
    @Override
    public void changePassword(UserEntity userEntity, String name) {
        String databasePassword = userRepository.getPassword(name);
        if (!passwordEncoder.matches(userEntity.getPassword(), databasePassword)) {
            throw new IllegalStateException("Wrong old password");
        }

        userRepository.changePassword(name,passwordEncoder.encode(databasePassword));
    }

    @Override
    public void changeInformation(UserEntity userEntity, String name) {
        userRepository.changeInformation(name,userEntity.getEmail(),userEntity.getName(),userEntity.getLastname());
    }

    @Override
    public CommentsEntity saveComment(CommentsEntity commentsEntity) {
        return commentsRepository.save(commentsEntity);
    }

    @Override
    public List<CommentsEntity> searchBySeriesId(Long id) {
        return commentsRepository.searchBySeriesId(id);
    }

    @Override
    public String getInformationAboutUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user with email " + email + "not found"));

        JsonObject json = new JsonObject();
        json.addProperty("account_id", user.getAccount_id());
        json.addProperty("email", user.getEmail());
        json.addProperty("name", user.getName());
        json.addProperty("lastname", user.getLastname());
        json.addProperty("role", user.getRole());
        json.addProperty("view", user.getView());

        return json.toString();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void updateSlider(SlidersEntity sliders) {
        slidersRepository.updateSlider(sliders.getSlider_id(),sliders.getUrl());
    }

    @Override
    public void deleteSlider(Long id) {
        slidersRepository.deleteSlider(id);
    }

    @Override
    public BoolForm checkEmail(String email) {

        BoolForm boolForm = new BoolForm();
        boolean userAlreadyExist = userRepository.findByEmail(email).isPresent();

        boolForm.setExists(userAlreadyExist);

        return boolForm;

    }

    @Override
    public void changeView(UserEntity userEntity, String name) {
        userRepository.changeView(name,userEntity.getView());
    }

    @Override
    public List<UserEntity> getRedactors() {
        return userRepository.getRedactors();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user with email " + email + "not found"));
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
