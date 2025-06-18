package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.ProductoService;
import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/admin")
    public String mostrarAdminPanel(Model model) { // 🔥 Agregamos `Model`

        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Pasamos `items`
        return "admin/dashboard";
    }
    @GetMapping("/admin/productos")
    public String gestionarProductos(Model model) {
        model.addAttribute("products", productoService.listarTodos());
        model.addAttribute("items", cartService.obtenerCarrito());
        return "admin/productos";
    }

    @GetMapping("/admin/usuarios")
    public String gestionarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "admin/usuarios";
    }
}