package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopSingleController {

    @Autowired
    private ProductoService productoService; // Servicio para manejar la lógica del producto

    @GetMapping("/shopsingle/{id}")
    public String shopsingle(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id);

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        model.addAttribute("producto", producto);
        model.addAttribute("imagenes", producto.getImagenes()); // Pasa las imágenes relacionadas
        return "shop-single";
    }




}
