package com.mianlodev.BetoStore.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarHashAdmin {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 1. ELIGE TU CONTRASEÑA SEGURA para el admin
        String password = "AdminSeg26"; // ¡CAMBIA ESTO!

        // 2. Genera el hash
        String hash = encoder.encode(password);

        // 3. Muestra el SQL listo para copiar
        System.out.println("==========================================");
        System.out.println("🔐 GENERADOR DE ADMINISTRADOR");
        System.out.println("==========================================");
        System.out.println("Contraseña elegida: " + password);
        System.out.println("Longitud del hash: " + hash.length() + " caracteres");
        System.out.println("\n📋 COPIA ESTE SQL Y EJECUTALO EN phpMyAdmin:");
        System.out.println("==========================================");

        System.out.println("-- 1. Eliminar admin existente si hay");
        System.out.println("DELETE FROM usuario_roles WHERE usuario_id IN ");
        System.out.println("  (SELECT id FROM usuarios WHERE username = 'admin');");
        System.out.println("DELETE FROM usuarios WHERE username = 'admin';");
        System.out.println();

        System.out.println("-- 2. Crear nuevo administrador");
        System.out.println("INSERT INTO usuarios (username, email, password)");
        System.out.println("VALUES (");
        System.out.println("    'adminseg',");
        System.out.println("    'admin@example.com',");
        System.out.println("    '" + hash + "'");
        System.out.println(");");
        System.out.println();

        System.out.println("-- 3. Asignar rol de administrador");
        System.out.println("INSERT INTO usuario_roles (usuario_id, rol)");
        System.out.println("SELECT id, 'ADMIN' FROM usuarios");
        System.out.println("WHERE username = 'admin';");
        System.out.println();

        System.out.println("-- 4. Verificar");
        System.out.println("SELECT ");
        System.out.println("    u.username,");
        System.out.println("    u.email,");
        System.out.println("    CHAR_LENGTH(u.password) as hash_length,");
        System.out.println("    ur.rol");
        System.out.println("FROM usuarios u");
        System.out.println("JOIN usuario_roles ur ON u.id = ur.usuario_id");
        System.out.println("WHERE u.username = 'admin';");
        System.out.println("==========================================");
        System.out.println("✅ Ahora puedes iniciar sesión con:");
        System.out.println("   Usuario: adminseg");
        System.out.println("   Contraseña: " + password);
        System.out.println("==========================================");

        // Verificación adicional
        boolean matches = encoder.matches(password, hash);
        System.out.println("🔍 Verificación: ¿El hash coincide con la contraseña? " +
                (matches ? "✅ SÍ" : "❌ NO"));
    }
}