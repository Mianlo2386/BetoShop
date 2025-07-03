package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.PasswordResetToken;
import com.mianlodev.BetoStore.service.RecuperacionService;
import com.mianlodev.BetoStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class RecuperacionController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RecuperacionService recuperacionService;

    @GetMapping("/recuperar")
    public String mostrarFormularioRecuperacion() {
        return "recuperar";
    }

    @PostMapping("/recuperar")
    public String procesarSolicitud(@RequestParam("email") String email,
                                    RedirectAttributes redirectAttributes) {

        usuarioService.buscarPorEmail(email)
                .ifPresent(recuperacionService::crearTokenParaUsuario);

        redirectAttributes.addFlashAttribute("exito",
                "Si el correo está registrado, recibirás un enlace para restablecer tu contraseña.");
        return "redirect:/login";
    }
    @GetMapping("/reset-password")
    public String mostrarFormularioNuevaPassword(@RequestParam("token") String token, Model model,
                                                 RedirectAttributes redirectAttributes) {

        Optional<PasswordResetToken> tokenOpt = recuperacionService.validarToken(token);

        if (tokenOpt.isEmpty() || tokenOpt.get().getExpiracion().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("error", "El enlace de recuperación es inválido o ha expirado.");
            return "redirect:/recuperar";
        }

        model.addAttribute("token", token);
        return "reset-password";
    }
}