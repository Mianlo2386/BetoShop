package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Promotion;
import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private CartService cartService;

    @GetMapping("/promociones")
    public String mostrarPromociones(Model model) {
        List<Promotion> promociones = promotionService.obtenerPromocionesActivas();
        model.addAttribute("promociones", promociones);
        model.addAttribute("items", cartService.obtenerCarrito()); // 🔥 Agregamos el carrito
        return "promociones";
    }
}