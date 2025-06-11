package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CartService cartService;

    @Value("${spring.mail.username}")
    private String emailDestinatario;

    @Value("${GOOGLE_MAPS_API}")
    private String googleMapsApiKey;

    @PostMapping("/contact")
    public String sendContactEmail(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message,
            Model model) {  // 🔥 Agregamos el modelo
        try {
            // ---- PRIMER CORREO: envío a mí mismo ----
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setTo(emailDestinatario);
            helper.setSubject(subject);
            helper.setText("Nombre: " + name + "\nEmail: " + email + "\n\nMensaje:\n" + message);

            mailSender.send(mail);

            // ---- SEGUNDO CORREO: respuesta automática al usuario ----
            MimeMessage confirmationMail = mailSender.createMimeMessage();
            MimeMessageHelper confirmationHelper = new MimeMessageHelper(confirmationMail, true);

            confirmationHelper.setTo(email); // correo del usuario
            confirmationHelper.setSubject("Gracias por contactarnos");
            confirmationHelper.setText("Hola " + name + ",\n\n"
                    + "Gracias por comunicarte con nosotros. Hemos recibido tu mensaje y te contactaremos a la brevedad.\n\n"
                    + "Saludos,\n"
                    + "El equipo de BetoStore.");

            mailSender.send(confirmationMail);

            // 🔥 Agregamos `items` al modelo antes de redirigir
            model.addAttribute("items", cartService.obtenerCarrito());

            return "redirect:/contact?success=true";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "redirect:/contact?error=true";
        }
    }

    @GetMapping("/contact")
    public String mostrarPaginaContacto(Model model) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Agregamos el carrito
        return "contact";
    }
}
