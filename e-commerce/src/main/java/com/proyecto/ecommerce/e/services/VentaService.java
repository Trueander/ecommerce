package com.proyecto.ecommerce.e.services;

import com.proyecto.ecommerce.e.models.Categoria;
import com.proyecto.ecommerce.e.models.Producto;
import com.proyecto.ecommerce.e.models.Venta;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaService {

    List<Categoria> listarCategorias();

    List<Producto> listarProductos();

    void realizarVenta(Venta venta);
}
