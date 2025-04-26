package com.mianlodev.BetoStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas accesibles para todos
                        .requestMatchers("/", "/index", "/about", "/shop", "/shop-single").permitAll()
                        .requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**").permitAll()
                        // Rutas protegidas (requieren login)
                        .requestMatchers("/contact", "/cart/**").authenticated()
                        .requestMatchers("/login", "/register").permitAll() // Login y registro accesibles sin autenticación
                        .anyRequest().authenticated() // Protege todo lo demás
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // Redirige al inicio tras login exitoso
                        .failureUrl("/login?error=true") // Redirige a login en caso de error
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // Redirige al inicio tras logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}