import { ItemVenta } from "./item-venta";

export class Venta {
    idVenta!: number;
    nombresCliente!: string;
    apellidosCliente!: string;
    telefonoCliente!: string;
    empresaDestino!: string;
    nombreCalle!: string;
    numeroCalle!: string;
    intCalle!: string;
    colonia!: string;
    codigoPostal!: string;
    ciudad!: string;
    estado!: string;
    pais!: string;
    total!: number;
    referencia!: string;
    items: ItemVenta[] = [];

    
    calcularImporteFinal(): number{
        this.total = 0;
        this.items.forEach((item: ItemVenta) => {
            let precio = item.cantidad * item.producto.precio;
            this.total += precio;
        });   
        return this.total;
    }
}
