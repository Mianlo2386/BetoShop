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

    @Value("${spring.mail.username}") // <-- Usamos directamente el mail de autenticación como destinatario
    private String emailDestinatario;

    @PostMapping("/contact")
    public String sendContactEmail(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setTo(emailDestinatario); // aquí cambiamos
            helper.setSubject(subject);
            helper.setText("Nombre: " + name + "\nEmail: " + email + "\n\nMensaje:\n" + message);

            mailSender.send(mail);
            return "redirect:/contact?success=true";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "redirect:/contact?error=true";
        }
    }
}
