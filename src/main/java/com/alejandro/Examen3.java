package com.alejandro;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Examen3 {
    public static void main(String[] args) throws IOException {
        // Datos de prueba
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Teclado", 45.99, LocalDate.of(2024, 1, 15)));
        productos.add(new Producto(2, "Ratón", 29.50, LocalDate.of(2024, 3, 22)));
        productos.add(new Producto(3, "Monitor", 299.00, LocalDate.of(2023, 11, 5)));
        // Parte A
        Producto.guardarProductos(productos);

        ArrayList<Producto> cargados = Producto.cargarProductos(); // Parte B
        cargados.forEach(System.out::println);

        Producto.subirPrecio10();                // Parte C
        Producto.cargarProductos().forEach(System.out::println);
    }
}
