package com.mianlodev.BetoStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        return "register"; // Este es el nombre de la plantilla register.html
    }

}
