package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.repository.UsuarioRepository;
import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        // Encriptar la contraseña
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());

        // Usar logger en lugar de System.out.println
        logger.info(">>> Contraseña encriptada: {}", encodedPassword);

        // Guardar usuario con la contraseña encriptada
        usuario.setPassword(encodedPassword);
        usuario.getRoles().add("ROLE_USER");

        return usuarioRepository.save(usuario);
    }
}
