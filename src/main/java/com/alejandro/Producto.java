package com.alejandro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    LocalDate fechaAlta;

    public Producto(int id, String nombre, double precio, LocalDate fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaAlta = fechaAlta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public static void guardarProductos(ArrayList<Producto> lista) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String json = gson.toJson(lista);
        Files.writeString(Path.of("productos.json"), json);
        System.out.println("✅ Guardado en productos.json");
    }

    public static ArrayList<Producto> cargarProductos() throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        String json = Files.readString(Path.of("productos.json"));

        Type tipo = new TypeToken<ArrayList<Producto>>(){}.getType();

        return gson.fromJson(json, tipo);
    }

    public static void subirPrecio10() throws IOException {
        ArrayList<Producto> lista = cargarProductos();

        for (Producto p : lista) {
            p.setPrecio(p.getPrecio() * 1.10d);
        }

        guardarProductos(lista);
        System.out.println("Precios actualizados");
    }




    @Override
    public String toString() {
        return id + " | " + nombre + " | " + precio + "€ | " + fechaAlta;
    }
}
