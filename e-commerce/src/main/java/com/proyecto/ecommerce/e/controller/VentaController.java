package com.proyecto.ecommerce.e.controller;

import com.proyecto.ecommerce.e.models.Categoria;
import com.proyecto.ecommerce.e.models.Producto;
import com.proyecto.ecommerce.e.models.Venta;
import com.proyecto.ecommerce.e.services.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v0/ecommerce")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping("/productos")
    public ResponseEntity<?> listarProductos() {
        Map<String, Object> response = new HashMap<>();
        List<Producto> productos;
        try {
            productos = ventaService.listarProductos();

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al obtener los productos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Productos obtenidos con éxito");
        response.put("productos", productos);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/categorias")
    public ResponseEntity<?> listarCategorias() {
        Map<String, Object> response = new HashMap<>();
        List<Categoria> categorias;
        try {
            categorias = ventaService.listarCategorias();

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al obtener las categorías.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Categorías obtenidas con éxito");
        response.put("categorias", categorias);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/venta")
    public ResponseEntity<?> realizarVenta(@Valid @RequestBody Venta venta, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            ventaService.realizarVenta(venta);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la venta.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Venta realizada con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }



}
