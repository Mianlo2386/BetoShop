package com.mianlodev.BetoStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${GOOGLE_MAPS_API}") // Obtiene la clave de la variable de entorno
    private String googleMapsApiKey;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("/shopsingle")
    public String shopsingle() {
        return "shop-single";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        return "contact";
    }
}
