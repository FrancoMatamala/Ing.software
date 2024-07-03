package com.ingco.anticipo.ingco_control_anticipos.authenticate.securities;

import java.util.Arrays;
import java.util.List;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.securities.filter.JWTAuthenticationFilter;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.securities.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/register").hasAnyRole("ADMIN", "BOSS")
                        .requestMatchers(HttpMethod.GET, "/api/collaborator").hasAnyRole("ADMIN", "BOSS")
                        .requestMatchers(HttpMethod.POST, "/api/collaborator").hasAnyRole("ADMIN", "BOSS")
                        .requestMatchers(HttpMethod.PUT, "/api/collaborator").hasAnyRole("ADMIN", "BOSS")
                        .requestMatchers(HttpMethod.GET, "/api/project").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/project").hasAnyRole("ADMIN", "BOSS")
                        .requestMatchers(HttpMethod.PUT, "/api/project").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/project").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/advance").hasAnyRole("ADMIN", "BOSS")
                        .requestMatchers(HttpMethod.POST, "/api/advance").hasAnyRole("ADMIN", "COLLABORATOR")
                        .anyRequest().authenticated())
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}
