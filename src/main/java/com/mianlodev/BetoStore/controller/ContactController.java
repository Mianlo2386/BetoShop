package com.mianlodev.BetoStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailDestinatario;

    @PostMapping("/contact")
    public String sendContactEmail(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message) {
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

            return "redirect:/contact?success=true";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "redirect:/contact?error=true";
        }
    }
}
