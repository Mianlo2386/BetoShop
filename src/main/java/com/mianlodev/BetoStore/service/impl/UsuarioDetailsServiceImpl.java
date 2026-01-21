package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // CONVERTIR los Strings a GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String rol : usuario.getRoles()) {
            // Si el rol es "ADMIN", convertirlo a "ROLE_ADMIN"
            String authority = rol.startsWith("ROLE_") ? rol : "ROLE_" + rol;
            authorities.add(new SimpleGrantedAuthority(authority));
        }

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                authorities  // ¡Ahora sí es List<GrantedAuthority>!
        );
    }
}