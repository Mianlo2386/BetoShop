package com.mianlodev.BetoStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String mostrarAdminPanel() {
        return "admin"; // Aquí irá el HTML que diseñes para administradores
    }
}