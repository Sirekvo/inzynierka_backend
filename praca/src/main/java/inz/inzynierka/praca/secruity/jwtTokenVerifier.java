package inz.inzynierka.praca.secruity;

import inz.inzynierka.praca.GlobalVariables;
import inz.inzynierka.praca.services.UserServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class jwtTokenVerifier extends OncePerRequestFilter {
    private final UserServiceImpl jwtUserDetailsService;
    private final JwTokenUtil jwtTokenUtil;

    public jwtTokenVerifier(@Lazy UserServiceImpl jwtUserDetailsService, JwTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
//        if(authorizationHeader != null) {
//            if (StringUtils.startsWith(authorizationHeader, "Bearer ")) {
//                String jwtToken = authorizationHeader.substring(7);
//                logger.warn(jwtToken);
//                try {
//                    String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//                    if (StringUtils.isNotEmpty(username)
//                            && null == SecurityContextHolder.getContext().getAuthentication()) {
//                        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
//                        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                                    new UsernamePasswordAuthenticationToken(
//                                            userDetails, null, userDetails.getAuthorities());
//                            usernamePasswordAuthenticationToken
//                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                            SecurityContextHolder.getContext()
//                                    .setAuthentication(usernamePasswordAuthenticationToken);
//                        }
//                    }
//                } catch (IllegalArgumentException e) {
//                    logger.error("Unable to fetch JWT Token");
//                } catch (ExpiredJwtException e) {
//                    logger.error("JWT Token is expired");
//                } catch (Exception e) {
//                    logger.error(e.getMessage());
//                }
//            } else {
//                logger.warn("JWT Token does not begin with Bearer String");
//            }
//        }
//        filterChain.doFilter(request, response);

        if (Strings.isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(GlobalVariables.getToken_key().getBytes())).build().parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String username = body.getSubject();
//            String username = jwtTokenUtil.getUsernameFromToken(token);
//            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            var authorities = (List<Map<String,String>>) body.get("roles");

            Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());

            Authentication authentication =  new UsernamePasswordAuthenticationToken(username,null,simpleGrantedAuthoritySet);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (JwtException e)
        {
            throw new IllegalStateException("Token cannot be trusted");
        }
        catch (IllegalArgumentException e) {
            logger.error("Unable to fetch JWT Token");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        filterChain.doFilter(request,response);
    }
//    private final UserServiceImpl jwtUserDetailsService;
//    private final JwTokenUtil jwtTokenUtil;
//
//    public jwtTokenVerifier(@Lazy UserServiceImpl jwtUserDetailsService, JwTokenUtil jwtTokenUtil) {
//        this.jwtUserDetailsService = jwtUserDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        final String requestTokenHeader = request.getHeader("Authorization");
//
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "GET");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
//
//
//        if(requestTokenHeader != null) {
//            if (StringUtils.startsWith(requestTokenHeader, "Bearer ")) {
//                String jwtToken = requestTokenHeader.substring(7);
//                try {
//                    String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//                    if (StringUtils.isNotEmpty(username)
//                            && null == SecurityContextHolder.getContext().getAuthentication()) {
//                        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
//                        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                                    new UsernamePasswordAuthenticationToken(
//                                            userDetails, null, userDetails.getAuthorities());
//                            usernamePasswordAuthenticationToken
//                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                            SecurityContextHolder.getContext()
//                                    .setAuthentication(usernamePasswordAuthenticationToken);
//                        }
//                    }
//                } catch (IllegalArgumentException e) {
//                    logger.error("Unable to fetch JWT Token");
//                } catch (ExpiredJwtException e) {
//                    logger.error("JWT Token is expired");
//                } catch (Exception e) {
//                    logger.error(e.getMessage());
//                }
//            } else {
//                logger.warn("JWT Token does not begin with Bearer String");
//            }
//        }
//        chain.doFilter(request, response);
//    }
}