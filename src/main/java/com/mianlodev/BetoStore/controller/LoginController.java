package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor // 🔥 Lombok maneja la inyección por constructor
public class LoginController {
    private final CartService cartService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Agregamos el carrito
        return "login";
    }
}