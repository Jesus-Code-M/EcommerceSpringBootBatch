package ecommerce_springbootbatch.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desactivar CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator/prometheus", "/actuator/**").permitAll()  // Permitir acceso público
                        .requestMatchers("/api/products/**", "/api/orders/**", "/api/batch/**", "/api/test/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());  // Usar autenticación básica
        return http.build();
    }
}


