package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private CartService cartService;

    @GetMapping("/admin")
    public String mostrarAdminPanel(Model model) { // 🔥 Agregamos `Model`

        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Pasamos `items`
        return "admin"; // Aquí irá el HTML que diseñes para administradores
    }
}