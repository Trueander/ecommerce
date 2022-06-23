import { Component, OnInit } from '@angular/core';
import { Venta } from 'src/app/models/venta';
import { VentaService } from 'src/app/services/venta.service';

@Component({
  selector: 'app-ventas',
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css']
})
export class VentasComponent implements OnInit {

  ventas: Venta[] = [];

  constructor(private ventaService: VentaService) { }

  ngOnInit(): void {
    this.ventaService.validarSesion();
    this.ventaService.obtenerVentas()
        .subscribe(response => this.ventas = response.ventas);
  }

  cerrarSesion(){
    this.ventaService.cerrarSesion();
  }

}
