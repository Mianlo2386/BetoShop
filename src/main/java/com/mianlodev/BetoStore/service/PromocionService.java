package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.Promocion;
import java.util.List;

public interface PromocionService {
    List<Promocion> obtenerPromocionesActivas();
    List<Promocion> obtenerPromocionesPorProducto(Long productoId);
    Promocion guardarPromocion(Promocion promocion);
    void eliminarPromocion(Long id);
}