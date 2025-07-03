package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.repository.UsuarioRepository;
import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        logger.info(">>> Contraseña encriptada: {}", encodedPassword);
        usuario.setPassword(encodedPassword);
        usuario.getRoles().add("ROLE_USER");
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    @Override
    public void actualizarPassword(Usuario usuario, String nuevaPassword) {
        String encoded = passwordEncoder.encode(nuevaPassword);
        usuario.setPassword(encoded);
        usuarioRepository.save(usuario);
    }
}
