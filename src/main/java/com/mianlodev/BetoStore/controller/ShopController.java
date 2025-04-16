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

        if (query != null && !query.isEmpty()) {
            productos = productoService.buscarPorNombreOCategoriaOSubcategoria(query);
            model.addAttribute("mensaje", "Resultados para: " + query);
        } else {
            productos = productoService.obtenerTodos(); // Si no hay búsqueda, muestra todos los productos
            model.addAttribute("mensaje", "Todos los productos");
        }

        model.addAttribute("productos", productos);
        return "shop"; // Redirige a la vista shop.html
    }
}

