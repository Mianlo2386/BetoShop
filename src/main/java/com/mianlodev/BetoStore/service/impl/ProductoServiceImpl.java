package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.repository.ProductoRepository;
import com.mianlodev.BetoStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }


    @Override
    public void eliminarPorId(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> obtenerReleases() {
        LocalDate fechaLimite = LocalDate.now().minusDays(30);
        return productoRepository.obtenerReleases(fechaLimite);
    }

    @Override
    public List<Producto> buscarPorNombreCategoriaSubcategoria(String search) {
        return productoRepository.buscarPorNombreCategoriaSubcategoria(search);
    }
    @Override
    public void actualizarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll(Sort.by("nombre").ascending());
    }

    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Producto no encontrado con ID: " + id));
    }


}