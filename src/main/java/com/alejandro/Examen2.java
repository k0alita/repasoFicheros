package com.alejandro;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Examen2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la ruta base: ");
        Path rutaBase = Path.of(sc.nextLine());

        System.out.print("Introduce la extensión (sin punto, ej: txt): ");
        String extension = "." + sc.nextLine();

        if (!Files.exists(rutaBase) || !Files.isDirectory(rutaBase)) {
            System.out.println("❌ La ruta no existe o no es un directorio.");
            return;
        }

        Path resultado = Path.of("resultado.txt");

        try (Stream<Path> arbol = Files.walk(rutaBase, 5);
            PrintWriter writer = new PrintWriter(Files.newBufferedWriter(resultado))) {

            arbol.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(extension))
                    .forEach(path -> {
                        writer.println(path.toAbsolutePath());
                        System.out.println("Encontrado: " + path.toAbsolutePath());
                    });

            System.out.println("✅ Resultado guardado en resultado.txt");

        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
