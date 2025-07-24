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
import java.io.UnsupportedEncodingException;



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
            Model model) {
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

            confirmationHelper.setTo(email);
            confirmationHelper.setSubject("Gracias por contactarte con el equipo de BetoStore");
            confirmationHelper.setFrom("no-reply@betostore.com", "Equipo de BetoStore");

            String respuestaHtml = """
            <html>
            <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                <div style="background-color: #fff; padding: 20px; border-radius: 8px;">
                    <h2 style="color: #28a745;">¡Gracias por contactarnos, %s!</h2>
                    <p>Hemos recibido tu mensaje y te responderemos a la brevedad.</p>
                    <p>Mientras tanto, podés visitar nuestro sitio o seguirnos en redes.</p>
                    <p style="margin-top: 30px;">Saludos,<br><strong>El equipo de BetoStore</strong></p>
                    <hr>
                    <p style="font-size: 12px; color: #888;">Este correo fue generado automáticamente. No respondas a este mensaje.</p>
                </div>
            </body>
            </html>
            """.formatted(name);

            confirmationHelper.setText(respuestaHtml, true);

            mailSender.send(confirmationMail);

            model.addAttribute("items", cartService.obtenerCarrito());

            return "redirect:/contact?success=true";
        } catch (MessagingException | UnsupportedEncodingException e) {
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
