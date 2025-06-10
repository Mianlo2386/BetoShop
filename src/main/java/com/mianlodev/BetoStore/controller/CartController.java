package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.CartItem;
import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.mianlodev.BetoStore.service.CartService;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductoService productoService;

    @PostMapping("/add")
    public String gestionarCarrito(@RequestParam Long productId, @RequestParam int quantity, @RequestParam String submit) {
        Producto producto = productoService.obtenerPorId(productId);

        if (producto != null && producto.getStock() >= quantity) {
            if ("addtocard".equals(submit)) {
                cartService.agregarProducto(producto, quantity);
                return "redirect:/cart";  // Redirigir al carrito
            } else if ("buy".equals(submit)) {
                cartService.procesarCompra(producto, quantity);
                return "redirect:/checkout";  // Redirigir a la compra
            }
        } else {
            System.out.println("Stock insuficiente para el producto: " + producto.getNombre());
        }

        return "redirect:/shop";
    }

    @GetMapping
    public String mostrarCarrito(Model model) {
        List<CartItem> carrito = cartService.obtenerCarrito();
        model.addAttribute("items", carrito);
        return "cart";
    }

    @PostMapping("/delete")
    public String eliminarProducto(@RequestParam Long productId) {
        cartService.eliminarProducto(productId);
        return "redirect:/cart";
    }
}