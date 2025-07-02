package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.ProductoService;
import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/admin/productos/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("items", cartService.obtenerCarrito());
        return "admin/producto-formulario";
    }

    @PostMapping("/admin/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminarPorId(id);
            redirectAttributes.addFlashAttribute("exito", "Producto eliminado correctamente.");
        } catch (EmptyResultDataAccessException e) {
            redirectAttributes.addFlashAttribute("error", "El producto ya no existe o fue eliminado.");
        }
        return "redirect:/admin/productos";
    }
    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("items", cartService.obtenerCarrito());
        return "admin/usuarios"; // Vista a crear
    }
}