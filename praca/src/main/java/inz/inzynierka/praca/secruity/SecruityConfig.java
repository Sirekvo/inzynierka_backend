package inz.inzynierka.praca.secruity;

import com.fasterxml.jackson.databind.ObjectMapper;
//import inz.inzynierka.praca.services.UserService;
import inz.inzynierka.praca.services.UserServiceImpl;
import inz.inzynierka.praca.services.UserServices;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecruityConfig extends WebSecurityConfigurerAdapter {

//    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final jwtTokenVerifier jwtTokenVerifier;

    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new AuthenticationFilter(authenticationManagerBean()));
        http.addFilterAfter(jwtTokenVerifier, AuthenticationFilter.class);
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

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
