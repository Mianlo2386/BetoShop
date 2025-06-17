package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodos();
    Producto guardarProducto(Producto producto);
    Producto obtenerPorId(Long id);
    void eliminarPorId(Long id);
    List<Producto> obtenerReleases(); // Solo la declaración del método
    List<Producto> buscarPorNombreCategoriaSubcategoria(String search);
    public void actualizarProducto(Producto producto);
    List<Producto> listarTodos();

}