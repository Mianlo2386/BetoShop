package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String mostrarProductos(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Producto> productos;

        if (query != null && !query.trim().isEmpty()) {
            productos = productoService.buscarPorNombreCategoriaSubcategoria(query);
            System.out.println("Productos encontrados para '" + query + "': " + productos.size());
        } else {
            productos = productoService.obtenerTodos();
            System.out.println("Mostrando todos los productos: " + productos.size());
        }

        model.addAttribute("productos", productos);
        model.addAttribute("mensaje", (query != null && !query.trim().isEmpty()) ? "Resultados para: " + query : "Todos los productos");

        return "shop";
    }
}