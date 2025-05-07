package com.mianlodev.BetoStore.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

public class TestPasswordEncoder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("🔐 Ingrese la contraseña original (en texto plano):");
        String rawPassword = scanner.nextLine();

        System.out.println("🔑 Ingrese la contraseña codificada desde la base de datos:");
        String encodedPassword = scanner.nextLine();

        boolean match = encoder.matches(rawPassword, encodedPassword);

        System.out.println("\n✅ ¿Coinciden?: " + match);
        if (!match) {
            System.out.println("⚠️ No coinciden. Posibles causas:");
            System.out.println("- La contraseña no se guardó encriptada con BCrypt.");
            System.out.println("- El usuario ingresó mal la contraseña.");
            System.out.println("- El PasswordEncoder en Spring Security no es BCrypt.");
        } else {
            System.out.println("🎉 La contraseña es válida.");
        }
    }
}

