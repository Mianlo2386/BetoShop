package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.PasswordResetToken;
import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecuperacionService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private JavaMailSender mailSender;

    public void crearTokenParaUsuario(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        tokenRepository.findByUsuario(usuario).ifPresent(tokenRepository::delete);

        PasswordResetToken prt = new PasswordResetToken();
        prt.setToken(token);
        prt.setUsuario(usuario);
        prt.setExpiracion(LocalDateTime.now().plusHours(1));
        tokenRepository.save(prt);

        enviarCorreoRecuperacion(usuario.getEmail(), token);

        System.out.println("🔗 Link de recuperación: http://localhost:8080/reset-password?token=" + token);
    }

    public Optional<PasswordResetToken> validarToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void eliminarToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }



    public void enviarCorreoRecuperacion(String toEmail, String token) {
        String link = "http://localhost:8080/reset-password?token=" + token;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(toEmail);
        mensaje.setSubject("Restablecer tu contraseña");
        mensaje.setText("Hola!\n\nRecibimos tu solicitud de recuperación. Usá este enlace para restablecer tu contraseña:\n\n" + link + "\n\nSi no fuiste vos, ignorá este mensaje.");

        mailSender.send(mensaje);
    }
}