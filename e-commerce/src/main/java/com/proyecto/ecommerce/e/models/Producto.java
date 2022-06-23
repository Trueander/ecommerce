package com.proyecto.ecommerce.e.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "nombre_producto")
    @NotEmpty(message = "no puede estar vacío")
    private String nombreProducto;

    @Column(name = "precio")
    @NotNull(message = "no puede estar vacío")
    private Double precio;

    @Column(name = "imagen")
    @NotEmpty(message = "no puede estar vacío")
    private String imagen;

    @Column(name = "descripcion")
    @NotEmpty(message = "no puede estar vacío")
    private String descripcion;

    @ManyToOne
    @JoinColumn(nullable = false, name = "categoria_id")
    @JsonIgnoreProperties({"productos", "hibernateLazyInitializer", "handler"})
    @NotNull(message = "no puede estar vacío")
    private Categoria categoria;

}
