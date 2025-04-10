package com.mianlodev.BetoStore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "products") // Mapeo para la tabla
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nombre;

    @Column(name = "description")
    private String descripcion;

    @Column(name = "price")
    private Double precio;

    @Column(name = "image_url")
    private String imagenUrl;

    @Column(name = "category")
    private String categoria;

    @Column(name = "subcategory")
    private String subcategoria;

    public List<String> getImagenes() {
        return Arrays.asList(
                this.imagenUrl, // Imagen principal del producto
                "https://i.imgur.com/MfJDP86.png", // Imagen secundaria
                "https://i.imgur.com/G7oeG49.png"  // Imagen adicional
        );
    }

}