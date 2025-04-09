package com.mianlodev.BetoStore;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Autowired
    private ProductoService productoService; // Inyección correcta del servicio

    @Value("${GOOGLE_MAPS_API}")
    private String googleMapsApiKey;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

//    @GetMapping("/shopsingle/{id}")
//    public String shopsingle(@PathVariable Long id, Model model) {
//        Producto producto = productoService.obtenerPorId(id);
//        System.out.println(producto); // Imprime el producto para verificar
//        System.out.println(producto.getImagenes());
//        model.addAttribute("producto", producto);
//        model.addAttribute("imagenes", producto.getImagenes());
//        return "shop-single";
//    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        return "contact";
    }
}
