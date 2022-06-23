import { Categoria } from "./categoria";

export class Producto {
    idProducto!: number;
    nombreProducto!: string;
    precio!: number;
    imagen!: string;
    descripcion!: string;
    categoria!: Categoria;
}