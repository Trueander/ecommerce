package com.proyecto.ecommerce.e.repos;

import com.proyecto.ecommerce.e.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepo extends CrudRepository<Producto, Long> {
}
