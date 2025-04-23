package com.mianlodev.BetoStore.repository;

import com.mianlodev.BetoStore.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void testBuscarProductoPorCategoriaDecoracion() {
        // Definir la categoría del producto existente
        String categoria = "Decoración";

        // Buscar productos por categoría
        List<Producto> productosEncontrados = productoRepository.findByCategoria(categoria);

        // Validar que el producto existe en la base de datos
        assertFalse(productosEncontrados.isEmpty(), "Debería haber productos en la categoría " + categoria);

        // Opcionalmente validar el nombre del producto
        Assertions.assertEquals("Cuadro de Anime One Piece", productosEncontrados.get(0).getNombre());
    }
    @Test
    void testProductoTieneImagenesAsociadas() {
        Producto producto = productoRepository.findById(1L).orElseThrow();
        assertFalse(producto.getImagenes().isEmpty(), "El producto debería tener imágenes asociadas");
    }
    @Test
    void testEliminarProducto() {
        productoRepository.deleteById(1L);
        assertFalse(productoRepository.findById(1L).isPresent(), "El producto debería eliminarse correctamente");
    }
}