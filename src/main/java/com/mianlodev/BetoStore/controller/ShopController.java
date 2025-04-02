package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String mostrarShop(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        return "shop";
    }
}