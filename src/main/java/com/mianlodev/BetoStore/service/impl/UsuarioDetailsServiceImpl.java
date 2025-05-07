package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioDetailsServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(">>> Buscando usuario: {}", username);

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(usuario.getRoles().toArray(new String[0]))
                .build();
    }
}


