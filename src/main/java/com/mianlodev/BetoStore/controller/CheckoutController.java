package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor // 🔥 Inyección por constructor con Lombok
public class CheckoutController {
    private final CartService cartService;

    @GetMapping("/checkout")
    public String mostrarCheckout(Model model) {
        model.addAttribute("items", cartService.obtenerCarrito());
        model.addAttribute("total", calcularTotal(cartService.obtenerCarrito()));
        return "checkout";
    }

    @PostMapping("/checkout/confirmar")
    public String confirmarCompra(RedirectAttributes redirectAttributes) {
        cartService.vaciarCarrito();
        redirectAttributes.addFlashAttribute("compraExitosa", true); // 🔥 Enviamos una señal al frontend
        return "redirect:/checkout"; // Mantenemos al usuario en la página
    }

    /*@GetMapping("/checkout/confirmado")
    public String mostrarConfirmacionCompra(Model model) {
        return "checkout-confirmado"; // 🔥 Vista de agradecimiento por la compra
    }*/

    private double calcularTotal(List<CartItem> items) {
        if (items == null || items.isEmpty()) return 0.0; // 🔥 Evita NullPointerException
        return items.stream()
                .mapToDouble(item -> item.getProducto().getPrecio() * item.getQuantity())
                .sum();
    }
}
