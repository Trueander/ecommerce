import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Venta } from '../models/venta';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  urlBackend: string = 'http://localhost:8080/api/v0/ecommerce';

  constructor(private http: HttpClient) { }

  obtenerProductos(): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/productos`);
  }

  obtenerCategorias(): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/categorias`);
  }

  realizarPedido(venta: Venta): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}/venta`,venta);
  }

}
