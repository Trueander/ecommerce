package com.proyecto.ecommerce.e.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    private Double total;

    @NotEmpty(message = "tiene que tener mínimo un producto")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "venta_id")
    private List<ItemVenta> items;

    @Column(name = "nombres_cliente")
    @NotEmpty(message = "no puede estar vacío")
    private String nombresCliente;

    @Column(name = "apellidos_cliente")
    @NotEmpty(message = "no puede estar vacío")
    private String apellidosCliente;

    @Column(name = "telefono_cliente")
    @NotEmpty(message = "no puede estar vacío")
    private String telefonoCliente;

    @Column(name = "empresa_destino")
    private String empresaDestino;

    @Column(name = "nombre_calle")
    @NotEmpty(message = "no puede estar vacío")
    private String nombreCalle;

    @Column(name = "numero_calle")
    @NotEmpty(message = "no puede estar vacío")
    private String numeroCalle;

    @Column(name = "int_calle")
    @NotEmpty(message = "no puede estar vacío")
    private String intCalle;

    private String referencia;

    @NotEmpty(message = "no puede estar vacío")
    private String colonia;

    @Column(name = "codigo_postal")
    @NotEmpty(message = "no puede estar vacío")
    private String codigoPostal;

    @NotEmpty(message = "no puede estar vacío")
    private String ciudad;

    @NotEmpty(message = "no puede estar vacío")
    private String estado;

    @NotEmpty(message = "no puede estar vacío")
    private String pais;




}
