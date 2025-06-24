package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.ProductoService;
import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/admin/productos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("items", cartService.obtenerCarrito()); // 🔧 clave para evitar el error
        return "admin/producto-formulario";
    }

    @PostMapping("/admin/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/admin/productos";
    }

}