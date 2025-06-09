package com.mianlodev.BetoStore.service.impl;

import com.mianlodev.BetoStore.model.CartItem;
import com.mianlodev.BetoStore.model.Producto;
import com.mianlodev.BetoStore.repository.CartRepository;
import com.mianlodev.BetoStore.service.CartService;
import com.mianlodev.BetoStore.service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductoService productoService;

    @Override
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.getStock() >= cantidad) {
            CartItem itemExistente = cartRepository.findByProducto_Id(producto.getId());

            if (itemExistente != null) {
                System.out.println("Agregando producto al carrito: " + producto.getNombre());
                itemExistente.setQuantity(itemExistente.getQuantity() + cantidad);
                cartRepository.save(itemExistente);
            } else {
                CartItem nuevoItem = new CartItem(producto, cantidad);
                cartRepository.save(nuevoItem);
                System.out.println("Producto guardado en carrito con ID: " + nuevoItem.getProducto().getId());
            }


            producto.setStock(producto.getStock() - cantidad);
            productoService.actualizarProducto(producto);
        }
    }

    @Override
    public List<CartItem> obtenerCarrito() {
        return cartRepository.findAll();
    }



    @Transactional
    public void eliminarProducto(Long productId) {
        cartRepository.deleteByProducto_Id(productId);
        System.out.println("Producto eliminado del carrito con ID: " + productId);
    }

    @Override
    public void procesarCompra(Producto producto, int cantidad) {
        if (producto.getStock() >= cantidad) {
            cartRepository.deleteByProducto_Id(producto.getId()); // Intenta eliminar el producto específico
            cartRepository.deleteAll(); // Asegura que todo el carrito se vacía
            producto.setStock(producto.getStock() - cantidad);
            productoService.actualizarProducto(producto);
            System.out.println("Compra procesada: " + producto.getNombre());
        } else {
            System.out.println("Stock insuficiente para completar la compra.");
        }
    }
}