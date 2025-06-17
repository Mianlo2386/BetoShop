package com.mianlodev.BetoStore.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin1234"));
    }
}

