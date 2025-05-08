package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.Promocion;
import com.mianlodev.BetoStore.repository.PromocionRepository;
import com.mianlodev.BetoStore.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromocionServiceImpl implements PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    @Override
    public List<Promocion> obtenerPromocionesActivas() {
        LocalDate hoy = LocalDate.now();
        return promocionRepository.findAll().stream()
                .filter(p -> p.getFechaInicio().isBefore(hoy) && p.getFechaFin().isAfter(hoy))
                .toList();
    }

    @Override
    public List<Promocion> obtenerPromocionesPorProducto(Long productoId) {
        return promocionRepository.findByProductoId(productoId);
    }

    @Override
    public Promocion guardarPromocion(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    @Override
    public void eliminarPromocion(Long id) {
        promocionRepository.deleteById(id);
    }
}