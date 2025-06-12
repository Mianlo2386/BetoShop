package com.mianlodev.BetoStore.service;

import com.mianlodev.BetoStore.model.CartItem;
import com.mianlodev.BetoStore.model.Producto;

import java.util.List;

public interface CartService {
    void agregarProducto(Producto producto, int cantidad);
    List<CartItem> obtenerCarrito();
    void eliminarProducto(Long productId);
    void procesarCompra(Producto producto, int cantidad);

    void vaciarCarrito();

}