import { Producto } from "./producto";

export class Categoria {
    idCategoria!: number;
    nombre!: string;
    productos: Array<Producto> = [];
}