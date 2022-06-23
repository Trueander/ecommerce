package com.proyecto.ecommerce.e.services;

import com.proyecto.ecommerce.e.models.Producto;
import com.proyecto.ecommerce.e.models.Usuario;
import com.proyecto.ecommerce.e.models.Venta;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Producto guardarProducto(Producto producto);
    Optional<Producto> buscarProductoPorId(Long id);
    List<Producto> listarProductos();
    Boolean eliminiarProductoPorId(Long id);
    Optional<Usuario> login(String usuario, String password);
    List<Venta> listarVentas();
}
