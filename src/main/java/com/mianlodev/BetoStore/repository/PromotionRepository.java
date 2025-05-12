package com.mianlodev.BetoStore.repository;

import com.mianlodev.BetoStore.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promocion, Long> {
    List<Promocion> findByFechaFinAfterAndFechaInicioBefore(LocalDate fechaFin, LocalDate fechaInicio);
}