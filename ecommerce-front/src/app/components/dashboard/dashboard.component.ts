import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/models/producto';
import { VentaService } from 'src/app/services/venta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  productos: Producto[] = [];

  constructor(private ventaService: VentaService, private router: Router) { }

  ngOnInit(): void {
    this.ventaService.validarSesion();
    this.ventaService.obtenerProductos()
        .subscribe(response => this.productos = response.productos);
  }

  
  eliminarProducto(producto: Producto): void {
    
    Swal.fire({
      title: 'Eliminar producto',
      text: `¿Está seguro de eliminar el producto ${producto.nombreProducto}?`,
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#28a745',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.ventaService.eliminarProductoPorId(producto.idProducto)
            .subscribe(response => {
              this.productos = this.productos.filter(pro => pro !== producto);
            });
        Swal.fire(
          'Eliminado!',
          `El producto ${producto.nombreProducto} ha sido eliminado.`,
          'success'
        )
      }
    });
  }

  cerrarSesion() {
    this.ventaService.cerrarSesion()
  }

}
