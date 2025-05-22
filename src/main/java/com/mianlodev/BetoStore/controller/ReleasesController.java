package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/releases")
public class ReleasesController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String mostrarReleases(Model model) {
        List<Producto> releases = productoService.obtenerReleases();
        model.addAttribute("releases", releases);
        return "releases";
    }
}
