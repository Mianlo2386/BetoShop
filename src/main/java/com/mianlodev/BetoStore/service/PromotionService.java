package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.Promocion;
import java.util.List;

public interface PromotionService {
    List<Promocion> obtenerPromocionesActivas();
}