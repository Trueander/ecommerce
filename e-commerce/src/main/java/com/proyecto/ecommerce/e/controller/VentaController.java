package com.proyecto.ecommerce.e.controller;

import com.proyecto.ecommerce.e.models.Categoria;
import com.proyecto.ecommerce.e.models.Producto;
import com.proyecto.ecommerce.e.models.Usuario;
import com.proyecto.ecommerce.e.models.Venta;
import com.proyecto.ecommerce.e.services.AdminService;
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
    private final AdminService adminService;

    @GetMapping("/productos")
    public ResponseEntity<?> listarProductos() {
        Map<String, Object> response = new HashMap<>();
        List<Producto> productos;
        try {
            productos = adminService.listarProductos();

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

    @GetMapping("/ventas")
    public ResponseEntity<?> listarVentas() {
        Map<String, Object> response = new HashMap<>();
        List<Venta> ventas;
        try {
            ventas = adminService.listarVentas();

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al obtener las ventas.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Ventas obtenidas con éxito");
        response.put("ventas", ventas);

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

    @PostMapping("/productos/crear-producto")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Producto productoNuevo = adminService.guardarProducto(producto);
            response.put("mensaje", "Producto creado con éxito");
            response.put("producto", productoNuevo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al crear el producto.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/productos/{idProducto}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Long idProducto) {
        Map<String, Object> response = new HashMap<>();
        Optional<Producto> productoOptional;
        try {
            productoOptional = adminService.buscarProductoPorId(idProducto);
            if(!productoOptional.isPresent()){
                response.put("mensaje", "No existe producto con ese ID");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("mensaje", "Busqueda exitosa");
            response.put("producto", productoOptional.get());
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al obtener las categorías.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/productos/editar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto){
        Map<String, Object> response = new HashMap<>();
        Optional<Producto> productoOptional;
        try {
            productoOptional = adminService.buscarProductoPorId(idProducto);
            if(!productoOptional.isPresent()){
                response.put("mensaje", "No existe producto con ese ID");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            Producto productoEncontrado = productoOptional.get();
            productoEncontrado.setNombreProducto(producto.getNombreProducto());
            productoEncontrado.setDescripcion(producto.getDescripcion());
            productoEncontrado.setPrecio(producto.getPrecio());
            productoEncontrado.setImagen(producto.getImagen());
            productoEncontrado.setCategoria(producto.getCategoria());
            adminService.guardarProducto(productoEncontrado);

            response.put("mensaje", "Actualización exitosa");
            response.put("producto", productoEncontrado);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al obtener las categorías.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/productos/eliminar/{idProducto}")
    public ResponseEntity<?> eliminar(@PathVariable Long idProducto) {

        Map<String, Object> response = new HashMap<>();
        Optional<Producto> productoOptional;

        try {
            productoOptional = adminService.buscarProductoPorId(idProducto);
            if(!productoOptional.isPresent()){
                response.put("mensaje", "No existe producto con ese ID");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            adminService.eliminiarProductoPorId(productoOptional.get().getIdProducto());
            response.put("mensaje", "El producto ha sido eliminado con éxito!");
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el producto en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/login/{usuario}/{password}")
    public ResponseEntity<?> login(@PathVariable String usuario, @PathVariable String password){
        Map<String, Object> response = new HashMap<>();
        Optional<Usuario> usuarioOptional;
        try {
            usuarioOptional = adminService.login(usuario, password);
            if(!usuarioOptional.isPresent()){
                response.put("mensaje", "Usuario y/o contraseña incorrecto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("mensaje", "Login exitoso");
            response.put("usuario", usuarioOptional.get());
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al logearse en el sistema");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
