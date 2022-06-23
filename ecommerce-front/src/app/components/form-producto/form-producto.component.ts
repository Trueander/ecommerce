import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/models/categoria';
import { Producto } from 'src/app/models/producto';
import { VentaService } from 'src/app/services/venta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-producto',
  templateUrl: './form-producto.component.html',
  styleUrls: ['./form-producto.component.css']
})
export class FormProductoComponent implements OnInit {

  producto: Producto = new Producto();
  categorias: Categoria[] = [];
  errores: string[] = [];

  constructor(private ventaService: VentaService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.ventaService.validarSesion();
    this.ventaService.obtenerCategorias()
        .subscribe(response => {
          this.categorias = response.categorias;
        });


        this.activatedRoute.params
        .subscribe(params => {
          let id: number = +params['idProducto'];

          if(id){
            this.ventaService.obtenerProductoPorId(id)
              .subscribe(response => {
                
                this.producto = response.producto
              });  
          }
        });
  }

  crear(): void {
    this.ventaService.crearProducto(this.producto)
        .subscribe( response => {
          this.router.navigate(['/dashboard']);
          Swal.fire({
            title:'Producto Creado',
            text: `El producto ${this.producto.nombreProducto} ha sido creado`,
            icon: 'success'
          });
        },
        err => {
          this.errores = err.error.errors as string[];
        }
        );
  }

  actualizar(): void {
    this.ventaService.actualizarProducto(this.producto)
        .subscribe( response => {
          this.router.navigate(['/dashboard']);
          Swal.fire({
            title:'Producto Actualizado',
            text: `El producto ${this.producto.nombreProducto} ha sido actualizado`,
            icon: 'success'
          });
        },
        err => {
          this.errores = err.error.errors as string[];
        }
        );
  }

  compararCategoria(o1: Categoria, o2:Categoria): boolean{
    if(o1 === undefined && o2 === undefined) return true;
    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false: o1.idCategoria == o2.idCategoria;
  }

  cerrarSesion() {
    this.ventaService.cerrarSesion()
  }

}
