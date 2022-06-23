package com.proyecto.ecommerce.e.repos;

import com.proyecto.ecommerce.e.models.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepo extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByUsuarioAndPassword(String usuario, String password);
}
