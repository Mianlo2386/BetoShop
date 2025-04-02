package com.mianlodev.BetoStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mianlodev.BetoStore.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Métodos personalizados opcionales
    // Por ejemplo: List<Producto> findByCategoria(String categoria);
}