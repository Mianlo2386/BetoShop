package com.mianlodev.BetoStore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
    public String shopsingle() { return "shop-single";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}

