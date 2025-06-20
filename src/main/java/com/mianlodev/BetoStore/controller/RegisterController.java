package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CartService cartService;

    @GetMapping("/register")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Agregamos el carrito
        return "register"; // Nombre del HTML
    }

    @PostMapping("/register")
    public String procesarRegistro(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/login?registroExitoso";
    }
}
