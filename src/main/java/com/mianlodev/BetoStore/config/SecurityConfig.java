package com.mianlodev.BetoStore.config;

import com.mianlodev.BetoStore.service.impl.UsuarioDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UsuarioDetailsServiceImpl usuarioDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return usuarioDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
