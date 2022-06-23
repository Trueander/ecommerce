package com.proyecto.ecommerce.e.services;

import com.proyecto.ecommerce.e.models.Categoria;
import com.proyecto.ecommerce.e.models.Producto;
import com.proyecto.ecommerce.e.models.Venta;
import com.proyecto.ecommerce.e.repos.VentaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepo ventaRepo;


    @Override
    public List<Categoria> listarCategorias() {
        return ventaRepo.listarCategorias();
    }


    @Override
    public void realizarVenta(Venta venta) {
        ventaRepo.save(venta);
    }
}
