import { Producto } from './producto';

export class ItemVenta {
    idItemVenta!: number;
    cantidad: number = 1;
    producto!: Producto;

}
