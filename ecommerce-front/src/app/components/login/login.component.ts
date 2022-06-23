import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VentaService } from 'src/app/services/venta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: string = '';
  password: string = '';

  constructor(private ventaService: VentaService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.ventaService.login(this.usuario, this.password)
        .subscribe(response => {
          Swal.fire(response.mensaje, `Bienvenido al sistema`, 'success');
          
          window.sessionStorage.setItem('LOGIN', 'ACTIVO');
          this.router.navigate(['/dashboard'])
        }, err => Swal.fire('Usuario y/o contraseña incorrecto', ``, 'warning'))
  }

}
