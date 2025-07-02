package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecuperacionController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/recuperar")
    public String mostrarFormularioRecuperacion() {
        return "recuperar"; // La vista HTML con el input de email
    }

    @PostMapping("/recuperar")
    public String procesarSolicitud(@RequestParam("email") String email,
                                    RedirectAttributes redirectAttributes) {
        // Lógica a implementar en pasos 2 y 3
        redirectAttributes.addFlashAttribute("exito", "Si el correo está registrado, recibirás un enlace para restablecer tu contraseña.");
        return "redirect:/login";
    }
}