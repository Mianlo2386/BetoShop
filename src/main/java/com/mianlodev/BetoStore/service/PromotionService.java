package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.Promotion;
import java.util.List;

public interface PromotionService {
    List<Promotion> obtenerPromocionesActivas();
}