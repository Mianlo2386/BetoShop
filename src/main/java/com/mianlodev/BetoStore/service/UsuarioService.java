package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();

    Optional<Usuario> buscarPorEmail(String email);
    void actualizarPassword(Usuario usuario, String nuevaPassword);
}