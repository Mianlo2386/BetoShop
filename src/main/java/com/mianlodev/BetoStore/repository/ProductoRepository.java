package com.mianlodev.BetoStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mianlodev.BetoStore.model.Producto;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :query, '%')) "
            + "OR LOWER(p.categoria) LIKE LOWER(CONCAT('%', :query, '%')) "
            + "OR LOWER(p.subcategoria) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Producto> buscarPorNombreCategoriaSubcategoria(@Param("query") String query);
    @Query("SELECT p FROM Producto p WHERE p.releaseDate >= :fechaLimite ORDER BY p.releaseDate DESC")
    List<Producto> obtenerReleases(@Param("fechaLimite") LocalDate fechaLimite);
    List<Producto> findByCategoria(String categoria);

    List<Producto> findByPrecioBetween(Double min, Double max);



}