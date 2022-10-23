package inz.inzynierka.praca;

import inz.inzynierka.praca.entites.UserEntity;
import inz.inzynierka.praca.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PracaApplication{
    public static void main(String[] args) {
        SpringApplication.run(PracaApplication.class, args);
    }


}
