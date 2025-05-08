package com.mianlodev.BetoStore.repository;

import com.mianlodev.BetoStore.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromocionRepository extends JpaRepository<Promocion, Long> {
    List<Promocion> findByProductoId(Long productoId);
}
