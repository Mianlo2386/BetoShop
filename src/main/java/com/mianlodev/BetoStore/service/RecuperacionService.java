package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.PasswordResetToken;
import com.mianlodev.BetoStore.model.Usuario;
import com.mianlodev.BetoStore.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecuperacionService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public void crearTokenParaUsuario(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        tokenRepository.findByUsuario(usuario).ifPresent(tokenRepository::delete);

        PasswordResetToken prt = new PasswordResetToken();
        prt.setToken(token);
        prt.setUsuario(usuario);
        prt.setExpiracion(LocalDateTime.now().plusHours(1));
        tokenRepository.save(prt);

        System.out.println("🔗 Link de recuperación: http://localhost:8080/reset-password?token=" + token);
    }

    public Optional<PasswordResetToken> validarToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void eliminarToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }
}