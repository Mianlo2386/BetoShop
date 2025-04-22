package com.mianlodev.BetoStore.repository;

import com.mianlodev.BetoStore.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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
        Assertions.assertFalse(productosEncontrados.isEmpty(), "Debería haber productos en la categoría " + categoria);

        // Opcionalmente validar el nombre del producto
        Assertions.assertEquals("Cuadro de Anime One Piece", productosEncontrados.get(0).getNombre());
    }
}