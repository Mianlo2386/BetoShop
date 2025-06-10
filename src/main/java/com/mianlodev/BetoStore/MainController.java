package com.mianlodev.BetoStore;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CartService cartService;

    @Value("${GOOGLE_MAPS_API}")
    private String googleMapsApiKey;



    @GetMapping("/")
    public String home(Model model) {
        List<Producto> releases = productoService.obtenerReleases();

        // Agrupar manualmente en listas de 3 productos
        List<List<Producto>> grupos = new ArrayList<>();
        for (int i = 0; i < releases.size(); i += 3) {
            grupos.add(releases.subList(i, Math.min(i + 3, releases.size())));
        }

        model.addAttribute("releasesGrupos", grupos);
        model.addAttribute("items", cartService.obtenerCarrito()); // Agrega el carrito

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }


    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        return "contact";
    }
}
