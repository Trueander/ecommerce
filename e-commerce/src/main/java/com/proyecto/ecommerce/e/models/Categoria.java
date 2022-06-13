package com.proyecto.ecommerce.e.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private List<Producto> productos;
}
