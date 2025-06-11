package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mianlodev.BetoStore.service.CartService;

@Controller
public class ShopSingleController {

    @Autowired
    private ProductoService productoService; // Servicio para manejar la lógica del producto

    @Autowired
    private CartService cartService;

    @GetMapping("/shopsingle/{id}")
    public String shopsingle(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id);

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }
        System.out.println("Stars del producto: " + producto.getStars());

        model.addAttribute("producto", producto);
        model.addAttribute("imagenes", producto.getImagenes()); // Pasa las imágenes relacionadas
        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Agregamos el carrito

        return "shop-single";
    }

}
