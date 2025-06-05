package com.mianlodev.BetoStore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter @Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Producto producto;

    private int quantity;

    public CartItem() {}

    public CartItem(Producto producto, int quantity) {
        this.producto = producto;
        this.quantity = quantity;
    }
}