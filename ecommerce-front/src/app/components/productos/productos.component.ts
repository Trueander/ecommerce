import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/models/categoria';
import { ItemVenta } from 'src/app/models/item-venta';
import { Producto } from 'src/app/models/producto';
import { Venta } from 'src/app/models/venta';
import { VentaService } from 'src/app/services/venta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  productos: Producto[] = [];

  categorias: Categoria[] = [];

  productoAux: Producto[] = [];

  venta: Venta = new Venta();
  
  carritoActivo: boolean = false;
  formActivo: boolean = false;

  constructor(private ventaService: VentaService) { }

  ngOnInit(): void {
    this.venta.pais= 'México';
    
    this.ventaService.obtenerProductos()
        .subscribe(response => {
          this.productos = response.productos;
          this.productoAux = this.productos;
        })
    this.ventaService.obtenerCategorias()
        .subscribe(response => this.categorias = response.categorias)
  }

  productosPorCategoria(categoria: Categoria) {
    this.productos = [];
    this.productos = categoria.productos
  }

  seleccionarTodos(){
    this.productos = this.productoAux
  }

  seleccionarProducto(producto: Producto) {
    let encontrado: boolean = false;
    
    this.venta.items.forEach(i => {
      if(i.producto.idProducto === producto.idProducto) {
        i.cantidad++;
        encontrado = true;
        
      }
    })

    if(!encontrado) {
      let itemVenta: ItemVenta = new ItemVenta();
      itemVenta.producto = producto;
      this.venta.items.push(itemVenta)
    }
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: 'Producto agregado',
      showConfirmButton: false,
      timer: 1400
    })
    
    
  }

  eliminarCartItem(itemVenta: ItemVenta) {
    Swal.fire({
      text: `¿Eliminar ${itemVenta.producto.nombreProducto} del pedido? `,
      showCancelButton: true,
      confirmButtonColor: '#28a745',
      cancelButtonColor: '#d33',
      cancelButtonText: 'No',
      confirmButtonText: 'Si'
    }).then((result) => {
      if (result.isConfirmed) {
        this.venta.items = this.venta.items.filter((itemP: ItemVenta) => itemVenta.producto.idProducto !== itemP.producto.idProducto);
        Swal.fire(
          'Eliminado!',
          `El producto ${itemVenta.producto.nombreProducto} ha sido eliminado.`,
          'success'
        )
      }
    });
  }

  bajarCantidad(itemVenta: ItemVenta){
    if(itemVenta.cantidad < 2){
      Swal.fire({
        text: `¿Eliminar ${itemVenta.producto.nombreProducto} del pedido? `,
        showCancelButton: true,
        confirmButtonColor: '#28a745',
        cancelButtonColor: '#d33',
        cancelButtonText: 'No',
        confirmButtonText: 'Si'
      }).then((result) => {
        if (result.isConfirmed) {
          this.venta.items = this.venta.items.filter((itemP: ItemVenta) => itemVenta.producto.idProducto !== itemP.producto.idProducto);
          Swal.fire(
            'Eliminado!',
            `El producto ${itemVenta.producto.nombreProducto} ha sido eliminado.`,
            'success'
          )
        }
      });
    }else{
      itemVenta.cantidad--;
    }
  }

  aumentarCantidad(itemVenta: ItemVenta){
    itemVenta.cantidad++;
  }

  desactivarCarrito() {
    this.carritoActivo = false;
  }

  activarCarrito() {
    this.carritoActivo = true;
    this.formActivo = false;
  }

  activarFormulario() {
    this.formActivo = true;
  }

  realizarVenta() {

        Swal
        .fire({
          text: `Por favor confirme el pedido. `,
          showCancelButton: true,
          confirmButtonColor: '#28a745',
          cancelButtonColor: '#d33',
          cancelButtonText: 'No',
          confirmButtonText: 'Si',
        })
        .then((result) => {
          if (result.isConfirmed) {
            this.ventaService.realizarPedido(this.venta).subscribe(
              (response) => {
  
                Swal.fire({
                  confirmButtonColor: '#28a745',
                  confirmButtonText: 'Aceptar',
                  icon: 'success',
                  title: `${this.venta.nombresCliente} su pedido has sido realizado con éxito!`,
                  showConfirmButton: false,
                  timer: 2500,
                });

                this.carritoActivo = false;
                this.formActivo = false;
                this.venta = new Venta();
                this.venta.pais = 'México';
              },
              (err) => {
                console.log(err)
                Swal.fire('Upps','Rellene los campos obligatorios','error')
              }
            );
          }
        });
  }

}
