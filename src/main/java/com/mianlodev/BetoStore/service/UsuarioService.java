package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();

}
