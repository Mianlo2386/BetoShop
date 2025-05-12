package com.mianlodev.BetoStore.controller;

import com.mianlodev.BetoStore.model.Promocion;
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

    @GetMapping("/promociones")
    public String mostrarPromociones(Model model) {
        List<Promocion> promociones = promotionService.obtenerPromocionesActivas();
        model.addAttribute("promociones", promociones);
        return "promociones";
    }
}