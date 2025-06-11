package com.mianlodev.BetoStore;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor // 🔥 Lombok genera automáticamente el constructor con los servicios
public class MainController {

    private final ProductoService productoService;
    private final CartService cartService;

    @Value("${GOOGLE_MAPS_API}")
    private String googleMapsApiKey;

    @GetMapping("/")
    public String home(Model model) {
        List<Producto> releases = productoService.obtenerReleases();

        List<List<Producto>> grupos = new ArrayList<>();
        for (int i = 0; i < releases.size(); i += 3) {
            grupos.add(releases.subList(i, Math.min(i + 3, releases.size())));
        }

        model.addAttribute("releasesGrupos", grupos);
        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Agregamos el carrito

        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("items", cartService.obtenerCarrito());
        return "about";
    }
}