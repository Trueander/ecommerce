import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FormProductoComponent } from './components/form-producto/form-producto.component';
import { LoginComponent } from './components/login/login.component';
import { ProductosComponent } from './components/productos/productos.component';
import { VentasComponent } from './components/ventas/ventas.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'productos', component: ProductosComponent},
  {path: 'dashboard/productos/crear', component: FormProductoComponent},
  {path: 'dashboard/productos/actualizar/:idProducto', component: FormProductoComponent},
  {path: 'dashboard/ventas', component: VentasComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
