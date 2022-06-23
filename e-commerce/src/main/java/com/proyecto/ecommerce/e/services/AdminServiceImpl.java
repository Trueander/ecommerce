package com.proyecto.ecommerce.e.services;

import com.proyecto.ecommerce.e.models.Producto;
import com.proyecto.ecommerce.e.models.Usuario;
import com.proyecto.ecommerce.e.models.Venta;
import com.proyecto.ecommerce.e.repos.ProductoRepo;
import com.proyecto.ecommerce.e.repos.UsuarioRepo;
import com.proyecto.ecommerce.e.repos.VentaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{

    private final ProductoRepo productoRepo;
    private final UsuarioRepo usuarioRepo;
    private final VentaRepo ventaRepo;

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepo.save(producto);
    }

    @Override
    public Optional<Producto> buscarProductoPorId(Long id) {
        return productoRepo.findById(id);
    }

    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) productoRepo.findAll();
    }

    @Override
    public Boolean eliminiarProductoPorId(Long id) {
        return buscarProductoPorId(id)
                .map(p -> {
                    productoRepo.deleteById(p.getIdProducto());
                    return true;
                }).orElse(false);
    }

    @Override
    public Optional<Usuario> login(String usuario, String password) {
        return usuarioRepo.findByUsuarioAndPassword(usuario, password);
    }

    @Override
    public List<Venta> listarVentas() {
        return (List<Venta>) ventaRepo.findAll();
    }
}
