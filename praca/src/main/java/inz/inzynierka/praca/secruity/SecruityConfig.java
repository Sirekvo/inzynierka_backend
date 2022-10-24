package inz.inzynierka.praca.secruity;

import com.fasterxml.jackson.databind.ObjectMapper;
//import inz.inzynierka.praca.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecruityConfig extends WebSecurityConfigurerAdapter {

//    private final UserService userService;
    private final UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new AuthenticationFilter(authenticationManagerBean()));
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST,"/login").permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
//
//                    Map<String, Object> responseMap = new HashMap<>();
//                    ObjectMapper mapper = new ObjectMapper();
//                    response.setStatus(401);
//                    responseMap.put("error", true);
//                    responseMap.put("message", "Unauthorized");
//                    response.setHeader("content-type", "application/json");
//                    String responseMsg = mapper.writeValueAsString(responseMap);
//                    response.getWriter().write(responseMsg);
//
//                }).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(new AuthenticationFilter(authenticationManagerBean()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
