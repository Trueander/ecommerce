import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Producto } from '../models/producto';
import { Venta } from '../models/venta';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  urlBackend: string = 'http://localhost:8080/api/v0/ecommerce';

  constructor(private http: HttpClient, private router: Router) { }

  obtenerProductos(): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/productos`);
  }

  obtenerCategorias(): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/categorias`);
  }

  obtenerVentas(): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/ventas`);
  }

  realizarPedido(venta: Venta): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}/venta`,venta);
  }

  obtenerProductoPorId(idProducto: number): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/productos/${idProducto}`);
  }

  crearProducto(producto: Producto): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}/productos/crear-producto`,producto);
  }

  eliminarProductoPorId(idProducto: number): Observable<any> {
    return this.http.delete<any>(`${this.urlBackend}/productos/eliminar/${idProducto}`);
  }

  actualizarProducto(producto: Producto): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}/productos/editar/${producto.idProducto}`,producto);
  }

  login(usuario: string, password: string): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/login/${usuario}/${password}`);
  }

  validarSesion() {
    let loginActivo = window.sessionStorage.getItem('LOGIN');
    if(!loginActivo){
      this.router.navigate(['/']);
      Swal.fire('No tienes accesos','Por favor inicie sesión','info')
    }
  }

  cerrarSesion() {
    window.sessionStorage.removeItem('LOGIN');
    this.router.navigate(['/']);
  }

}
