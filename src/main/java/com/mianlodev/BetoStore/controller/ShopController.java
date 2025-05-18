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
    public String mostrarProductos(@RequestParam(value = "search", required = false) String search, Model model) {
        System.out.println("Búsqueda recibida en ShopController: " + search);

        List<Producto> productos;
        if (search != null && !search.trim().isEmpty()) {
            productos = productoService.buscarPorNombreCategoriaSubcategoria(search);
            System.out.println("Productos encontrados para '" + search + "': " + productos.size());
        } else {
            productos = productoService.obtenerTodos();
            System.out.println("Mostrando todos los productos: " + productos.size());
        }

        model.addAttribute("productos", productos);
        model.addAttribute("mensaje", search != null && !search.trim().isEmpty() ? "Resultados para: " + search : "Todos los productos");

        return "shop";
    }

}