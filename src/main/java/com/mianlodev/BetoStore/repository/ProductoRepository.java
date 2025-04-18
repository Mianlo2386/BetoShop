package com.mianlodev.BetoStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mianlodev.BetoStore.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE " +
            "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.categoria) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.subcategoria) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Producto> buscarPorNombreOCategoriaOSubcategoria(@Param("query") String query);

    List<Producto> findByCategoria(String categoria);
}