package com.mianlodev.BetoStore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.mianlodev.BetoStore.model.ProductImage;

import java.time.LocalDate;
import java.util.ArrayList;

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

    @Column(name = "specifications", columnDefinition = "TEXT")
    private String especificaciones;

    @Column(name = "category")
    private String categoria;

    @Column(name = "subcategory")
    private String subcategoria;

    // Nueva columna size
    @Column(name = "size", columnDefinition = "TEXT")
    private String size;

    // Nueva columna stars
    @Column(name = "stars")
    private int stars;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "stock", nullable = false)
    private int stock;


    public List<String> getImagenes() {
        return imagenes.stream()
                .map(ProductImage::getImageUrl)
                .toList(); // Devuelve solo las URLs
    }

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductImage> imagenes = new ArrayList<>();



}