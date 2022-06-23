package com.proyecto.ecommerce.e.repos;

import com.proyecto.ecommerce.e.models.Categoria;
import com.proyecto.ecommerce.e.models.Producto;
import com.proyecto.ecommerce.e.models.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VentaRepo extends CrudRepository<Venta, Long> {

    @Query("FROM Categoria")
    List<Categoria> listarCategorias();
}
