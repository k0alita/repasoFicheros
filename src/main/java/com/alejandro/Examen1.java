package com.alejandro;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class Examen1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la ruta del directorio");
        Path ruta = Paths.get(sc.nextLine());

        if (!Files.exists(ruta) || !Files.isDirectory(ruta)) {
            System.out.println("La ruta no existe");
            return;
        }

        try (Stream<Path> archivos = Files.list(ruta)){
            archivos.filter(Files::isRegularFile).forEach(path -> {
                try {
                    long kb = Files.size(path) / 1024;
                    System.out.println(path.getFileName() + " - " + kb + " KB ");
                } catch (IOException e) {
                    System.out.println("Error leyendo: " + path.getFileName());
                }
            });
        }

        Path destino = ruta.resolve("filtrados");
        Files.createDirectories(destino);

        try (Stream<Path> archivos = Files.list(ruta)){
            archivos.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".txt"))
                    .filter(path -> {
                        try { return Files.size(path) > 500; }
                        catch (IOException e) { return false; }

                    })
                    .forEach(path -> {
                        try {
                            Files.copy(path, destino.resolve(path.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Copiado: " + path.getFileName());
                        } catch (IOException e) {
                            System.out.println("Error copiando: " + path.getFileName());
                        }
                    });
        }
    }
}
