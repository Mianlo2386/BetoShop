package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.Promocion;
import com.mianlodev.BetoStore.repository.PromotionRepository;
import com.mianlodev.BetoStore.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promocionRepository;

    @Override
    public List<Promocion> obtenerPromocionesActivas() {
        return promocionRepository.findByFechaFinAfterAndFechaInicioBefore(LocalDate.now(), LocalDate.now());
    }
}